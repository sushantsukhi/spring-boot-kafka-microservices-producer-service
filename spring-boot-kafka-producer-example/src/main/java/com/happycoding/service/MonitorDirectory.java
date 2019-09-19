package com.happycoding.service;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MonitorDirectory {

	@Autowired
	KafkaProducer kafkaProducer;

	@Autowired
	WordReader wordReader;

	@Value(value = "${directory.path}")
	String directoryPath;

	public void watchDirectory() {
		System.out.println("MonitorDirectory::watchDirectory started..");
		Path faxFolder = Paths.get(directoryPath);
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			faxFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

			boolean valid = true;
			do {
				WatchKey watchKey = watchService.take();

				for (WatchEvent<?> event : watchKey.pollEvents()) {
					//Kind<?> kind = event.kind();
					if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
						String fileName = event.context().toString();
						System.out.println("File Created:" + fileName);
						String fullPath = directoryPath + fileName;
						/*List<String> processWordDocMsgs = wordReader.processWordDoc(fullPath);
						for (String msg : processWordDocMsgs) {
							kafkaProducer.send(msg, "" + 11111, 1);
						}*/
						System.out.println("Message sent to the Kafka Topic java_in_use_topic Successfully");
					}
				}
				valid = watchKey.reset();
			} while (valid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MonitorDirectory::watchDirectory ended..");
	}

//	public static void main(String[] args) throws IOException, InterruptedException {
//		new MonitorDirectory().watchDirectory();
//	}
}
