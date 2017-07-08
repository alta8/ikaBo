package de.alta.ikariamBot.parser;

import static de.alta.ikariamBot.Util.sleep;
import static de.alta.ikariamBot.Util.sleepDuration;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import welt.City;
import welt.Insel;

public class InselReichParser {
	
	private static final String BASE_URL = "https://s17-de.ikariam.gameforge.com/index.php?view=island&islandId=";
	public final int MIN_ID = 0;
//	public final int MAX_ID = 87;
	public final int MAX_ID = 5721;
	
	private final InselParser inselParser;
	private final Path out;
	
	public InselReichParser(InselParser inselParser) 
	{
		this.inselParser = inselParser;
		//this.out = Paths.get("/home/alta/temp/ikaBot/out.txt");
		final LocalDateTime now = LocalDateTime.now();
		final String dateTimePart = now.format(DateTimeFormatter.ofPattern("YYYY_MM_dd-HHmm"));
		this.out = Paths.get("/media/alta/INTENSO/projs/ikaBo/data/out_" + dateTimePart + ".txt");
	}

	public List<Insel> parse() throws IOException {
		final List<Insel> inseln = new ArrayList<>();
		Files.createFile(out);
		try (BufferedWriter writer = Files.newBufferedWriter(out))
		{
		IntStream.rangeClosed(MIN_ID, MAX_ID).mapToObj(i -> BASE_URL + i)
		.peek(System.out::println)
		.map(ParserInput::new).map(InselParser::new).map(InselParser::parse)
		.forEach(i -> {
			System.out.println(i);
			if (!inseln.contains(i)) {
				inseln.add(i);
			}
			sleep(sleepDuration());
			try {
				writer.write(i.getJSON());
				writer.newLine();
				writer.flush();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}); 
		}
//		.collect(Collectors.toList());
		return inseln;
	}

	private String toCsv(Insel insel) {
		final StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(insel.getID());
		strBuilder.append(";");
		strBuilder.append(insel.getName());
		strBuilder.append(";");
		strBuilder.append(insel.getX());
		strBuilder.append(";");
		strBuilder.append(insel.getY());
		strBuilder.append(";");
		insel.getResources().stream().forEach(e -> {
		    strBuilder.append(e);
		    strBuilder.append(";");
		});
		for (City c : insel.getCities()) {
			strBuilder.append(";");
			strBuilder.append(c.getName());
			strBuilder.append(";");
			strBuilder.append(c.isInaktive());
		}
		strBuilder.append("\n");
		return strBuilder.toString();
	}

}
