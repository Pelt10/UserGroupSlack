package fr.pelt10.usergroupslack;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Boostrap {

    public static void main(String[] args) {
	System.out.println("Launching bots...");
	for (int i = 0; i < args.length; i+=2) {
	    new Bot(args[i], Arrays.stream(args[i+1].split(",")).collect(Collectors.toList()));
	}
	System.out.println("Bots launch.");
    }
}
