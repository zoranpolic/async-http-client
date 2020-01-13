package async;

import java.util.concurrent.*;

public class HttpClient {

    public void client1(String url, int numCalls, int maxSimulCalls) {
        for(int j = 0; j < numCalls/maxSimulCalls; j++) {
            ExecutorService executor = Executors.newFixedThreadPool(maxSimulCalls);

            for (int i = 0; i < maxSimulCalls; i++) {
                LinkedBlockingQueue queue = new LinkedBlockingQueue();
                queue.add(url + (j*10 + i));
                executor.execute(new HttpGetDoc(queue));
            }

            System.out.println("Waiting...");

            // No more threads can be submitted to the executor service!
            executor.shutdown();

            // Blocks until all numCalls/maxSimulCalls submitted threads have finished!
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println((j+1) + "-th batch of " + numCalls + " is done");
        }
        System.out.println("All is done");
    }

    public void client2(String url, int numCalls, int maxSimulCalls) {

        ExecutorService executor = Executors.newFixedThreadPool(maxSimulCalls);

        for(int j = 0; j < numCalls; j++) {
            LinkedBlockingQueue queue = new LinkedBlockingQueue();
            queue.add(url + (j + 1));
            executor.execute(new HttpGetDoc(queue));
        }

        System.out.println("Waiting...");

        // No more threads can be submitted to the executor service!
        executor.shutdown();

        // Blocks until all numCalls submitted threads have finished!
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All is done");
    }

    public void client3(String url, int numCalls, int maxSimulCalls) {

        ExecutorService executor = Executors.newFixedThreadPool(maxSimulCalls);

        for(int j = 0; j < numCalls; j++) {
            LinkedBlockingQueue queue = new LinkedBlockingQueue();
            queue.add(url + (j + 1));
            Future future = executor.submit(new HttpGetDoc(queue));

            // Blocks until currently submitted thread is finished!
            while(true){
                try {
                    if (future.get()==null) break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            };
            System.out.println((j+1) + "-th call is done");
        }

        // No more threads can be submitted to the executor service!
        executor.shutdown();
    }

}
