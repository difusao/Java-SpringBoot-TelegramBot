package com.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SimpleBot extends TelegramLongPollingBot {
	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			String command = update.getMessage().getText();
			SendMessage message = new SendMessage();

			message.enableMarkdown(true);
			message.setChatId(update.getMessage().getChatId().toString());

			if (command.startsWith("/") && command.split(" ")[0].equals("/run")) {
				String cmd = command.split(" ")[0];
				String param = (command.split(" ").length > 1 ? command.split(" ")[1] : "");

				message.setText("Executando comando... Feito!");

				System.out.println("Comando: " + cmd);
				System.out.println("Parâmetro: " + param);
			} else {
				message.setText("Entendi.");
			}

			try {
				execute(message);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getBotUsername() {
		return "BotName";
	}

	@Override
	public String getBotToken() {
		return "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	}
}