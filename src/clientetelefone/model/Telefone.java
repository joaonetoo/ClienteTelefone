/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetelefone.model;

import java.util.Objects;

public class Telefone {
    private int id;
    private int ddi;
    private int ddd;
    private String numero;
    private int clienteid;

    public Telefone() {
    }

    public Telefone(int ddi, int ddd, String numero) {
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
    }

    public Telefone(int id, int ddi, int ddd, String numero) {
        this.id = id;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
    }

    public Telefone(int ddi, int ddd, String numero, int clienteid) {
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
        this.clienteid = clienteid;
    }
   

    public int getDdi() {
        return ddi;
    }

    public void setDdi(int ddi) {
        this.ddi = ddi;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Telefone other = (Telefone) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.ddi != other.ddi) {
            return false;
        }
        if (this.ddd != other.ddd) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }

    public int getClienteid() {
        return clienteid;
    }

    public void setClienteid(int clienteid) {
        this.clienteid = clienteid;
    }

    @Override
    public String toString() {
        return "Telefone{" + "id=" + id + ", ddi=" + ddi + ", ddd=" + ddd + ", numero=" + numero + ", clienteid=" + clienteid + '}'+"\n";
    }
}
