package dev.pedrohb.chatfilterai;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.slf4j.Logger;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;

@SuppressWarnings("unused")
@Plugin(id = "chatfilterai", name = "ChatFilterAI", version = "1.0.0", authors = { "PedroHB" })
public final class ChatFilterAI {

  private final ProxyServer server;
  private final Logger logger;
  private final File dataFolder;
  private final File file;

  @Inject
  public ChatFilterAI(ProxyServer server, Logger logger, @DataDirectory Path dataFolder) {
    this.server = server;
    this.logger = logger;
    this.dataFolder = new File(dataFolder.toFile().getParentFile(), this.getClass().getAnnotation(Plugin.class).name());

    try {
      this.file = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
    } catch (URISyntaxException ex) {
      throw new RuntimeException(ex);
    }

    this.onLoad();
  }

  @Subscribe
  public void onProxyInitialization(ProxyInitializeEvent event) {
    this.onEnable();
  }

  @Subscribe
  public void onProxyShutdown(ProxyShutdownEvent event) {
    this.onDisable();
  }

  public void onLoad() {
    this.logger.info("ChatFilterAI loaded.");
  }

  public void onEnable() {
    this.logger.info("ChatFilterAI enabled.");
  }

  public void onDisable() {
    this.logger.info("ChatFilterAI disabled.");
  }
}
