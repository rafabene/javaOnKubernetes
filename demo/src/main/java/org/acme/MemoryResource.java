package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/memory")
public class MemoryResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Runtime rt = Runtime.getRuntime();
        StringBuilder sb = new StringBuilder();
        int x = 0;
        while (true) {
            long maxMemory = rt.maxMemory();
            long usedMemory = 0;
            try {
                while (((float) usedMemory / maxMemory) < 0.80) {
                    // Gera uma string randomica
                    sb.append(System.nanoTime() + System.nanoTime() + System.nanoTime() + System.nanoTime());
                    usedMemory = rt.totalMemory();
                    x++;
                    // A cada 2 repetições mostra a quantidade
                    // de memória consumida
                    if (x % 2 == 0) {
                        System.out.println("Memória do SO utilizada: "
                                + humanReadableByteCount(rt.totalMemory(), false));

                    }
                }
            } catch (OutOfMemoryError e) {
                System.out
                        .println("OutOfMemory. ALOCAÇÃO INTERROMPIDA. Memória do SO utilizada: "
                                + humanReadableByteCount(rt.totalMemory(), false));
            }
        }

    }

    private String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit)
            return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);

    }
}
