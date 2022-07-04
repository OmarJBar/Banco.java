/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc.beans;

import bc.entities.Clientes;
import bc.entities.Cuentas;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.el.PropertyNotFoundException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author omar
 */
@Named(value = "clienteBean")
@RequestScoped
public class clienteBean extends cuentaBean {

    @EJB
    private CuentasFacadeLocal cuentasFacade;

    @EJB
    private ClientesFacadeLocal clientesFacade;

    
    private String rut;
    private String nombre;
    private String ciudad;
    private int codigoCuenta;
    private String respuesta="Resultado: ";

    private Cuentas cuenta_;

    public clienteBean() {
        cuenta_ = new Cuentas();
    }

    public String crearCliente() {
        boolean ver=true;
        
        try {
            Clientes a = new Clientes();
            a.setRut(rut);
            a.setNombre(nombre);
            a.setCiudad(ciudad);
            a.setNumCuenta(codigoCuenta);
            clientesFacade.create(a);
            
            // aqui es porque la clase alumnoFacade tiene todos los elementos necesarios para crear, buscar, etc.
            // hago la cuenta aqui altiro 
            
            crearCuenta(codigoCuenta, 0);
        }catch (PropertyNotFoundException | EJBException | NullPointerException e) {
            setRespuesta(respuesta+"Error tipo: " + e);
            ver=false;
        }
        
        if (ver) {
            setRespuesta(respuesta+"completado");
        }
        return "cliente";
    }

    public List<Clientes> getClientes() {
        return clientesFacade.findAll();
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Cuentas getCuenta_() {
        return cuenta_;
    }

    public void setCuenta_(Cuentas cuenta_) {
        this.cuenta_ = cuenta_;
    }

    public int getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(int codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public CuentasFacadeLocal getCuentasFacade() {
        return cuentasFacade;
    }

    @Override
    public void setCuentasFacade(CuentasFacadeLocal cuentasFacade) {
        this.cuentasFacade = cuentasFacade;
    }

}
