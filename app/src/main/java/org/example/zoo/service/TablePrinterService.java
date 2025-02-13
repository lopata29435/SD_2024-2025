package com.example.zoo.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TablePrinterService {

    public void printTable(List<String> headers, List<List<String>> rows) {
        if (headers == null || rows == null || headers.isEmpty()) {
            throw new IllegalArgumentException("Headers and rows must not be null or empty.");
        }

        int[] columnWidths = new int[headers.size()];
        for (int i = 0; i < headers.size(); i++) {
            columnWidths[i] = headers.get(i).length();
        }

        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i).length() > columnWidths[i]) {
                    columnWidths[i] = row.get(i).length();
                }
            }
        }

        printLine(columnWidths);

        printRow(headers, columnWidths);

        printLine(columnWidths);

        for (List<String> row : rows) {
            printRow(row, columnWidths);
        }

        printLine(columnWidths);
    }

    private void printLine(int[] columnWidths) {
        System.out.print("+");
        for (int width : columnWidths) {
            System.out.print("-".repeat(width + 2) + "+");
        }
        System.out.println();
    }

    private void printRow(List<String> row, int[] columnWidths) {
        System.out.print("|");
        for (int i = 0; i < row.size(); i++) {
            String cell = row.get(i);
            System.out.print(" " + padRight(cell, columnWidths[i]) + " |");
        }
        System.out.println();
    }

    private String padRight(String s, int length) {
        return String.format("%-" + length + "s", s);
    }
}