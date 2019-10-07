package com.saltlux.shkim2.korean.automata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class Automata {
	public static void main(String args[]) throws IOException {

		if (args.length != 2) {
			System.out.println("please insert args[0]: input_file_path, args[1]: output_file_path");
			System.exit(0);
		}
		BufferedReader inputFile = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));
		BufferedWriter outputFile = new BufferedWriter(new FileWriter(args[1]));

		String input = null;
		String result = null;
		while ((input = inputFile.readLine()) != null) {
			result = AutomataStr(input);
			outputFile.write(result + "\n");
		}
		inputFile.close();
		outputFile.close();
	}

	public static String AutomataStr(String inputSent) {
		String[] tokens = inputSent.split(" ");
		StringBuilder result = new StringBuilder();
		for (int j = 0; j < tokens.length; j++) {
			int state = 0;
			String input = tokens[j];
			for (int i = 0; i < input.length() - 1; i++) {
				switch (state) {
				case 0:
					result.append('|');
					if (input.charAt(i) == 'g' || input.charAt(i) == 'n' || input.charAt(i) == 'd'
							|| input.charAt(i) == 'l' || input.charAt(i) == 'm' || input.charAt(i) == 'b'
							|| input.charAt(i) == 's' || // input.charAt(i)=='w'||
							input.charAt(i) == 'j' || input.charAt(i) == 'z' || input.charAt(i) == 'k'
							|| input.charAt(i) == 't' || input.charAt(i) == 'p' || input.charAt(i) == 'h') {

						result.append(input.charAt(i));
						state = 1;
					} else if (input.charAt(i) == 'w') {
						if (input.charAt(i + 1) == 'i') {
							result.append(input.charAt(i));
							state = 1;
						} else {
							result.append(input.charAt(i));
							state = 1;
						}
					} else {
						// error
					}
					break;
				case 1:
					if (input.charAt(i) == 'g' || input.charAt(i) == 'n' || input.charAt(i) == 'd'
							|| input.charAt(i) == 'l' || input.charAt(i) == 'm' || input.charAt(i) == 'b'
							|| input.charAt(i) == 's' || // input.charAt(i)=='w'||
							input.charAt(i) == 'j' || input.charAt(i) == 'z' || input.charAt(i) == 'k'
							|| input.charAt(i) == 't' || input.charAt(i) == 'p' || input.charAt(i) == 'h') {
						// case of a fortis
						// error
					} else if (input.charAt(i) == 'w') {
						if (input.charAt(i + 1) == 'i') {
							result.append(input.charAt(i));
							state = 2;
							// the case of 'ㅟ'
						} else {
							// the case of 'O'
							// error
						}
					} else {
						result.append(input.charAt(i));
						state = 2;
					}
					break;
				case 2:
					if (input.charAt(i) == 'g' || input.charAt(i) == 'n' || input.charAt(i) == 'd'
							|| input.charAt(i) == 'l' || input.charAt(i) == 'm' || input.charAt(i) == 'b'
							|| input.charAt(i) == 's' || input.charAt(i) == 'w' || input.charAt(i) == 'j'
							|| input.charAt(i) == 'z' || input.charAt(i) == 'k' || input.charAt(i) == 't'
							|| input.charAt(i) == 'p' || input.charAt(i) == 'h') {
						if (input.charAt(i + 1) == 'g' || input.charAt(i + 1) == 'n' || input.charAt(i + 1) == 'd'
								|| input.charAt(i + 1) == 'l' || input.charAt(i + 1) == 'm'
								|| input.charAt(i + 1) == 'b' || input.charAt(i + 1) == 's'
								|| input.charAt(i + 1) == 'w' || input.charAt(i + 1) == 'j'
								|| input.charAt(i + 1) == 'z' || input.charAt(i + 1) == 'k'
								|| input.charAt(i + 1) == 't' || input.charAt(i + 1) == 'p'
								|| input.charAt(i + 1) == 'h') {
							result.append(input.charAt(i));
							state = 3;
							// 받침이 하나로 끝난 경우 혹은 된소리 인 경우
						} else {
							// 받침이 없는 경우
							result.append('|');
							state = 0;
							i--;
						}
					} else {
						// 두 개의 모음이 결합된 경우
						result.append(input.charAt(i));
						state = 2;
					}
					break;

				case 3:
					if (input.charAt(i + 1) == 'g' || input.charAt(i + 1) == 'n' || input.charAt(i + 1) == 'd'
							|| input.charAt(i + 1) == 'l' || input.charAt(i + 1) == 'm' || input.charAt(i + 1) == 'b'
							|| input.charAt(i + 1) == 's' || input.charAt(i + 1) == 'w' || input.charAt(i + 1) == 'j'
							|| input.charAt(i + 1) == 'z' || input.charAt(i + 1) == 'k' || input.charAt(i + 1) == 't'
							|| input.charAt(i + 1) == 'p' || input.charAt(i + 1) == 'h') {
						// 된소리의 경우
						result.append(input.charAt(i));
						result.append('|');
						state = 0;
					} else {
						// 받침이 하나로 끝난 경우
						result.append('|');
						i--;
						state = 0;
					}
					break;
				}
			}
			result.append(input.charAt(input.length() - 1));
			result.append("| ");
		}
		return result.toString();
	}
}
