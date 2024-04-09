package br.com.crud.cruddb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataUtils {

    public boolean validarDataBrasileira(String data) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d\\d$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

    public Integer calcularIdade(String dataNascimento) {
        if (dataNascimento == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate dataNascimentoDate = LocalDate.parse(dataNascimento, formatter);
            LocalDate dataAtual = LocalDate.now();
            Period periodo = Period.between(dataNascimentoDate, dataAtual);
            return periodo.getYears();
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
