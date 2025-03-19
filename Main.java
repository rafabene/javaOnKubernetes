
public class Main {

    private long startTime = System.currentTimeMillis();

    class MinhaThread extends Thread {

        public void run() {
            this.setName("Thread Exemplo");
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
                    System.out.println("Operações executadas: " + x);
                    System.out.println("Tempo em milis até OO: " + (System.currentTimeMillis() - startTime));
                    System.out
                            .println("OutOfMemory. ALOCAÇÃO INTERROMPIDA. Memória do SO utilizada: "
                                    + humanReadableByteCount(rt.totalMemory(), false));
                    try {
                        Thread.sleep(1000 * 60);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("info")) {
            Runtime rt = Runtime.getRuntime();
            System.out.println("Processors: " + rt.availableProcessors());
            System.out.println("Total Memory: " + humanReadableByteCount(rt.maxMemory(), true));
        } else {
            System.out.println("Executando Thread");
            MinhaThread thread = new Main().new MinhaThread();
            thread.start();
        }
    }

    private static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit)
            return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

}