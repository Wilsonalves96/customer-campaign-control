package com.customer.campaign.control.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.customer.campaign.control.stream.Stream;
import com.customer.campaign.control.stream.StreamImpl;
import com.customer.campaign.control.utils.LetterUtils;

@RestController
@RequestMapping("/api/stream/")
public class StreamController {
	
	

	@GetMapping("/{value}")
	public ResponseEntity<String> getRequiredVowel(@PathVariable(value = "value") String value) {
		StreamImpl stream = new StreamImpl(value);

		Character vowel = getVowel(stream);

		if (vowel != null) {
			return ResponseEntity.ok().body(String.format(
					"Primeiro caractere vogal que não se repete, após uma consoante, onde a mesma é antecessora de uma vogal, no valor: %s é: '%s'",
					value, vowel));
		} else {
			return ResponseEntity.ok().body(
					"Não foi encontrado um caractere vogal que não se repete, após uma consoante, onde a mesma é antecessora de uma vogal");
		}
	}
	
	private boolean usedVowel(Set<String> usedVowels, Character letter) {
		String lower = "";

		lower += letter;
		
		return usedVowels.contains(lower.toLowerCase());
	}
	
	private void addVowel(Set<String> usedVowels, Character letter) {
		String lower = "";

		lower += letter;
		
		usedVowels.add(lower.toLowerCase());
	}

	private Character getVowel(Stream stream) {
		Set<String> usedVowels = new HashSet<String>();
		
		String value = "";

		Character letter = null;

		while (stream.hasNext()) {
			letter = stream.getNext();

			value += letter;

			if (value.length() > 2) {
				if (LetterUtils.isVowel(letter) && !usedVowel(usedVowels, letter)) {
					Character previousConsonant = LetterUtils.isPreviousConsonant(value, letter);
					if (previousConsonant != null) {
						if (LetterUtils.isPreviousVowel(value, previousConsonant)) {
							break;
						} else {
							value = value.substring(1);
						}
					} else {
						value = value.substring(1);
					}
				} else {
					value = value.substring(1);
				}
			}

			if (LetterUtils.isVowel(letter)) {				
				addVowel(usedVowels, letter);
			}

			if (usedVowels.size() == 5) {
				letter = null;

				break;
			}

			letter = null;
		}

		return letter;
	}
}
