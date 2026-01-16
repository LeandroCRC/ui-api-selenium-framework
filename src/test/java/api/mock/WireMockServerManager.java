package api.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class WireMockServerManager {

    private static WireMockServer wireMockServer;

    public static void startServer() {
        if (wireMockServer == null) {

            // Usamos puerto fijo para simplificar (8089)
            wireMockServer = new WireMockServer(
                WireMockConfiguration.options().port(8089)
            );

            wireMockServer.start();

            if (!wireMockServer.isRunning()) {
                throw new RuntimeException("WireMock server failed to start");
            }

            // Configuramos WireMock para localhost:8089
            configureFor("localhost", 8089);

            // Registramos los stubs
            registerStubs();

            System.out.println("[WireMock] Server started on port 8089");
        }
    }

    public static void stopServer() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
            wireMockServer = null;
            System.out.println("[WireMock] Server stopped");
        }
    }

    private static void registerStubs() {

        // POST /users/add
        stubFor(post(urlEqualTo("/users/add"))
            .willReturn(aResponse()
                .withStatus(201)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"id\":1,\"username\":\"lrojas\"}")));

        // GET /users/{id}
        stubFor(get(urlPathMatching("/users/\\d+"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"id\":1,\"username\":\"lrojas\",\"email\":\"leandro@test.com\",\"phone\":\"123456789\"}")));

        // PUT /users/{id}
        stubFor(put(urlPathMatching("/users/\\d+"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"id\":1,\"phone\":\"999999999\"}")));

        // DELETE /users/{id}
        stubFor(delete(urlPathMatching("/users/\\d+"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"id\":1,\"isDeleted\":true}")));
    }
}
