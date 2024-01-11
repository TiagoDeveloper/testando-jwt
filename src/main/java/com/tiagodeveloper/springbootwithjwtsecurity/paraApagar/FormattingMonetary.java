package com.tiagodeveloper.springbootwithjwtsecurity.paraApagar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

@FunctionalInterface
public interface FormattingMonetary {

    String getFormattedValue(BigDecimal value);

    default String valueWithoutRound(BigDecimal value) {
        NumberFormat numberFormat = DecimalFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        return numberFormat.format(value.setScale(2, RoundingMode.DOWN));
    }

    default String formatting(BigDecimal value) {
        DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.of("en", "US")));
        return decimalFormat.format(value.setScale(2, RoundingMode.DOWN));
    }

}
