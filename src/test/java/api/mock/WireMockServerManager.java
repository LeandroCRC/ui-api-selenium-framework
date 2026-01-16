package api.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class WireMockServerManager {

    private static WireMockServer wireMockServer;

    public static void startServer() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            return;
        }

        wireMockServer = new WireMockServer(
                WireMockConfiguration.options().dynamicPort()
        );

        wireMockServer.start();

        if (!wireMockServer.isRunning()) {
            throw new RuntimeException("WireMock server failed to start");
        }

        configureFor("localhost", wireMockServer.port());
        registerStubs();
    }

    public static void stopServer() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
            wireMockServer = null;
        }
    }

    public static String getBaseUrl() {
        if (wireMockServer == null || !wireMockServer.isRunning()) {
            throw new IllegalStateException("WireMock server is not running");
        }
        return "http://localhost:" + wireMockServer.port();
    }

    private static void registerStubs() {

        stubFor(post(urlEqualTo("/users/add"))
            .willReturn(aResponse()
                .withStatus(201)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"id\":1,\"username\":\"lrojas\"}")));

        stubFor(get(urlPathMatching("/users/\\d+"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"id\":1,\"username\":\"lrojas\",\"email\":\"leandro@test.com\",\"phone\":\"123456789\"}")));

        stubFor(put(urlPathMatching("/users/\\d+"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"id\":1,\"phone\":\"999999999\"}")));

        stubFor(delete(urlPathMatching("/users/\\d+"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"id\":1,\"isDeleted\":true}")));
    }
}

