package zona_fit.sistemazonafit.servicio;

import zona_fit.sistemazonafit.modelo.Cliente;

import java.util.List;

public interface IClienteServicio {
    public List<Cliente> listarTodos();

    List<Cliente> listarClientes();

    public Cliente buscarClientePorId(Integer idCliente);

    public void guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);



}
