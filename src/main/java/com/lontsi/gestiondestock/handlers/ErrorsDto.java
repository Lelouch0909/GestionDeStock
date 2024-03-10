package com.lontsi.gestiondestock.handlers;

import java.util.List;

import com.lontsi.gestiondestock.exception.ErrorsCodes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorsDto {

	private Integer httpcode;

	private ErrorsCodes code;

	private String message;

	private List<String> errors;

}
