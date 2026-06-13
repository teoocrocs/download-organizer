import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.nio.file.*;
import javax.swing.*;

public class Organizador {

    public static void main(String[] args) {
        // 1. Crear la ventana principal (JFrame)
        JFrame ventana = new JFrame("Organizador de Descargas");
        ventana.setSize(400, 200);
        
        // 🚨 ESTO ES CLAVE: Al cerrar la ventana con la "X", el proceso de Java muere definitivamente
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null); // Centrar en la pantalla

        // 2. Diseñar el interior de la ventana
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("⚡ Organizador Activo", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        
        JLabel subTitulo = new JLabel("<html><center>Monitoreando tu carpeta de descargas.<br>Cierra esta ventana para detener el programa.</center></html>", SwingConstants.CENTER);
        subTitulo.setFont(new Font("Arial", Font.PLAIN, 13));

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(subTitulo, BorderLayout.CENTER);
        ventana.add(panel);

        // 3. Hacer la ventana visible
        ventana.setVisible(true);

        // 4. Lanzar el vigilante en su hilo secundario
        Thread hiloVigilante = new Thread(() -> {
            correrVigilante();
        });
        hiloVigilante.setDaemon(true); 
        hiloVigilante.start();
    }

    private static void correrVigilante() {
        String carpetaDescargas = System.getProperty("user.home") + "/Downloads";
        Path rutaDescargas = Paths.get(carpetaDescargas);

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            rutaDescargas.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            while (true) {
                WatchKey key = watchService.take(); 
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        Path archivoCreado = rutaDescargas.resolve((Path) event.context());
                        Thread.sleep(1000); 

                        String nombreArchivo = archivoCreado.getFileName().toString();
                        String extension = getExtension(nombreArchivo);

                        if (extension.isEmpty() || extension.equals("crdownload") || extension.equals("part")) {
                            continue; 
                        }

                        Path carpetaDestino = Paths.get(carpetaDescargas, extension);
                        if (!Files.exists(carpetaDestino)) {
                            Files.createDirectory(carpetaDestino);
                        }

                        Files.move(archivoCreado, carpetaDestino.resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Hilo de vigilancia detenido.");
        }
    }

    private static String getExtension(String nombreArchivo) {
        int index = nombreArchivo.lastIndexOf('.');
        if (index > 0 && index < nombreArchivo.length() - 1) {
            return nombreArchivo.substring(index + 1).toLowerCase();
        }
        return ""; 
    }
}