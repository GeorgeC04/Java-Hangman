package com.company;

import java.util.Scanner;
import java.io.*;

class GuessClass{

	int characterAttempt=1;
	String GuessWord;
	Character[] PositionArray;


	Character [] CreateArray (){
		Character [] PositionArray = new Character[GuessWord.length()];
		for (int g=0; g< GuessWord.length(); g++){
			PositionArray[g] = 'n';
		}
		return PositionArray;
	}

	String InputWord (){
		Scanner NewScanner = new Scanner(System.in);
		System.out.print("Player should now enter the word they want others to guess");
		GuessWord = NewScanner.nextLine();
		return GuessWord;
	}

	Character InputGuess()throws java.io.IOException{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter character attempt number " + characterAttempt);
		char UserGuess = sc.next().charAt(0);
		characterAttempt ++;
		return UserGuess;
	}

	Character[] StringToArray (String GuessWord){
		int StringLength = GuessWord.length();
		Character[] GuessArray = new Character[StringLength];
		for (int count = 0; count < StringLength; count++){
			GuessArray[count] = GuessWord.charAt(count);
		}
		return GuessArray;
	}

	boolean IsInString(String GuessWord, Character[] GuessArray, Character GuessAttempt){
		boolean Found = false;
		int UserGuessLength = GuessWord.length();
		int ArrayCount = 0;
		for(ArrayCount = 0 ; ArrayCount < UserGuessLength; ArrayCount++){
			if(GuessAttempt == GuessArray[ArrayCount]) Found = true;
		}
		return Found;

	}

	Character [] CorrectPosition(Character [] GuessArray, Character UserGuess, String GuessWord){
		for(int l = 0; l<GuessWord.length(); l++){
			System.out.println(GuessArray[l]);
		}
		for(int f = 0; f<GuessWord.length(); f++){
			System.out.println(PositionArray[f]);
		}
		System.out.println(GuessWord.length());
		for (int positioncount = 0; positioncount < GuessWord.length(); positioncount++){
			if (GuessArray[positioncount] == UserGuess){
				PositionArray[positioncount] = UserGuess;
			}
			else{
				PositionArray[positioncount] = '|';
			}
			System.out.println(GuessArray[positioncount]);
		}
		return PositionArray;
	}
}

public class Main {



    public static void main(String[] args)throws java.io.IOException {
    	boolean AllCorrect = false;
    	int lengthCount = 0;
		int correctAmount = 0;
		GuessClass NewGuess = new GuessClass();
		String WordToFind = NewGuess.InputWord();
		Character[] StringArray = NewGuess.StringToArray(WordToFind);
		Character[] CorrectArray = new Character[WordToFind.length()];
		int GuessCount = 0;
		int CorrectGuess;
		boolean Found;
		while (GuessCount <= 4 & AllCorrect == false ){
			Character GuessCharacter = NewGuess.InputGuess();
			boolean CanBeFound = NewGuess.IsInString(WordToFind, StringArray, GuessCharacter);
			if(CanBeFound){
				 Found = true;
				 for (int l=0; l<WordToFind.length(); l++ ){
				 	if(StringArray[l] == GuessCharacter){
				 		CorrectArray[l] = GuessCharacter;
					}
				 }
			}
			else {
				Found = false;
				GuessCount++;
				correctAmount = 0;
			}
			if (GuessCount == 1 & Found == false){
				RandomAccessFile file1 = new RandomAccessFile("E:\\hangman\\step1.txt", "rw");
				file1.seek(3);
				for (int count = 0; count <= 5; count++) {
					String line = file1.readLine();
					System.out.println(line);
				}

				file1.close();
			}
			else if (GuessCount == 2 & Found == false){
				RandomAccessFile file2 = new RandomAccessFile("E:\\hangman\\step2.txt", "rw");
				file2.seek(1);
				for (int count = 0; count <= 5; count++) {
					String line = file2.readLine();
					System.out.println(line);
				}

				file2.close();
			}
			else if (GuessCount == 3 & Found == false){
				RandomAccessFile file3 = new RandomAccessFile("E:\\hangman\\step3.txt", "rw");
				file3.seek(1);
				for (int count = 0; count <= 5; count++) {
					String line = file3.readLine();
					System.out.println(line);
				}
				file3.close();
			}
			else if (GuessCount == 4 & Found == false){
				RandomAccessFile file4 = new RandomAccessFile("E:\\hangman\\step4.txt", "rw");
				file4.seek(1);
				for (int count = 0; count <= 5; count++) {
					String line = file4.readLine();
					System.out.println(line);
				}
				file4.close();
			}
			else if (GuessCount == 5 & Found == false){
				RandomAccessFile file5 = new RandomAccessFile("E:\\hangman\\completed.txt", "rw");
				file5.seek(2);
				for (int count = 0; count <= 5; count++) {
					String line = file5.readLine();
					System.out.println(line);
				}
				System.out.println("You lose, word to guess was " + WordToFind);
				file5.close();
			}
			else{
				for (int f=0; f<WordToFind.length(); f++){
					if(CorrectArray[f] == StringArray[f]) lengthCount++;
				}
				System.out.println(lengthCount);
				if (lengthCount == WordToFind.length()){
					AllCorrect = true;
					System.out.println("Well Done, you win, the word to guess was " + WordToFind);
				}
				else{
					Found = false;
					lengthCount = 0;
				}
			}


		}


    }
}
