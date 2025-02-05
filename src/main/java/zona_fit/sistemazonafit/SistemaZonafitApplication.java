package zona_fit.sistemazonafit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zona_fit.sistemazonafit.modelo.Cliente;
import zona_fit.sistemazonafit.servicio.IClienteServicio;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SistemaZonafitApplication implements CommandLineRunner {

    @Autowired
    private IClienteServicio clienteServicio;

    private static final Logger logger = LoggerFactory.getLogger(SistemaZonafitApplication.class);
    String nl = System.lineSeparator();

    public static void main(String[] args) {
        SpringApplication.run(SistemaZonafitApplication.class, args);
    }

    @Override
    public void run(String... args) {
        zonaFitApp();
    }

    private void zonaFitApp() {
        logger.info("\n***Aplicación Zona Fit (GYM) ***");
        var salir = false;
        var consola = new Scanner(System.in);
        while (!salir) {
            var opcion = mostrarMenu(consola);
            salir = ejecutarOpciones(consola, opcion, salir);
            logger.info("");
        }
    }

    private int mostrarMenu(Scanner consola) {
        logger.info("""
                Seleccione una opción:
                1. Listar clientes
                2. Buscar cliente
                3. Agregar Cliente
                4. Modificar cliente
                5. Eliminar Cliente
                6. Salir
                Elige una opción:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private boolean ejecutarOpciones(Scanner consola, int opcion, boolean salir) {
        switch (opcion) {
            case 1 -> {
                logger.info("{}Listado de clientes{}", nl, nl);
                List<Cliente> clientes = clienteServicio.listarClientes();
                clientes.forEach(cliente -> logger.info(cliente.toString() + nl));
            }
            case 2 -> {
                logger.info("Buscar cliente");
                logger.info("Ingrese el id del cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
                if (cliente != null) {
                    logger.info("Cliente encontrado: " + cliente + nl);
                } else {
                    logger.info("Cliente no encontrado" + nl);
                }
            }
            case 3 -> {
                logger.info("Agregar cliente{}", nl);
                logger.info("Ingrese el nombre del cliente: ");
                var nombre = consola.nextLine();
                logger.info("Ingrese el apellido del cliente: ");
                var apellido = consola.nextLine();
                logger.info("Ingrese la membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente();
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setMembresia(membresia);
                clienteServicio.guardarCliente(cliente);
                logger.info("Cliente agregado correctamente" + cliente + nl);

            }
            case 4 -> {
                logger.info("Modificar cliente");
                logger.info("Ingrese el id del cliente a modificar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
                if (cliente != null) {
                    logger.info("Ingrese el nombre del cliente: ");
                    var nombre = consola.nextLine();
                    logger.info("Ingrese el apellido del cliente: ");
                    var apellido = consola.nextLine();
                    logger.info("Ingrese la membresia: ");
                    var membresia = Integer.parseInt(consola.nextLine());
                    cliente.setNombre(nombre);
                    cliente.setApellido(apellido);
                    cliente.setMembresia(membresia);
                    clienteServicio.guardarCliente(cliente);
                    logger.info("Cliente modificado correctamente" + cliente + nl);
                } else {
                    logger.info("Cliente no encontrado" + cliente + nl);
                }
            }
            case 5 -> {
                logger.info("Eliminar cliente");
                logger.info("Ingrese el id del cliente a eliminar: ");
                var id = Integer.parseInt(consola.nextLine());
                var cliente = clienteServicio.buscarClientePorId(id);
                if (cliente != null) {
                    clienteServicio.eliminarCliente(cliente);
                    logger.info("Cliente eliminado correctamente" + cliente + nl);
                } else {
                    logger.info("Cliente no encontrado" + cliente + nl);
                }
            }
            case 6 -> {
                logger.info("Saliendo de la aplicación" + nl + "Hasta luego");
                salir = true;
            }
            default -> logger.info("Opción no válida" + opcion + nl);
        }
        return salir;
    }
}
