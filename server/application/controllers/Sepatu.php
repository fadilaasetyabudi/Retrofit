<?php

defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';
use Restserver\Libraries\REST_Controller;

class Sepatu extends REST_Controller {

    function __construct($config = 'rest') {
        parent::__construct($config);
        $this->load->database();
    }

    //Menampilkan data sepatu
    function index_get() {
        $id_spt = $this->get('id_spt');
        if ($id_spt == '') {
            $sepatu = $this->db->get('telepon')->result();
        } else {
            $this->db->where('id_spt', $id_spt);
            $sepatu = $this->db->get('telepon')->result();
        }
        $this->response(array('status' => 'OK', 'result' => $sepatu, 'message' => 'Success'), 200);
    }

    //Mengirim atau menambah data sepatu baru
    function index_post() {
        $data = array(
                    'id_spt'           => $this->post('id_spt'),
                    'size'          => $this->post('size'),
                    'color'    => $this->post('color'));
        $insert = $this->db->insert('telepon', $data);
        // if ($insert) {
        //     $this->response(array('status' => 'OK', 'result' => '', 'message' => 'Success'), 200);
        // } else {
        //     $this->response(array('status' => 'Fail', 'result' => '', 'message' => 'Failed'), 502);
        // }

        if ($insert) {
            $this->response($data, 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }

    //Memperbarui data sepatu yang telah ada
    function index_put() {
        $id_spt = $this->put('id_spt');
        $data = array(
                    'id_spt'       => $this->put('id_spt'),
                    'size'          => $this->put('size'),
                    'color'    => $this->put('color'));
        $this->db->where('id_spt', $id_spt);
        $update = $this->db->update('telepon', $data);
        if ($update) {
            $this->response($data, 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }

    //Menghapus salah satu data sepatu
    function index_delete() {
        $id_spt = $this->delete('id_spt');
        $this->db->where('id_spt', $id_spt);
        $delete = $this->db->delete('telepon');
        if ($delete) {
            $this->response(array('status' => 'success'), 201);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }

    //Masukan function selanjutnya disini
}
?>