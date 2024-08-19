package com.example.eco_recicla;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TableDynamic {
    private TableLayout tableLayout;
    private Context context;
    private String[] header;
    private ArrayList<String[]> data;
    private TableRow tableRow;
    private TextView txtCell;

    //se necesecita la posicion para obtener cada dato del array
    private int indexColumn;
    private int indexRow;

    public TableDynamic(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public void addHeader(String[] header) {
        this.header = header;
        createHeader();
    }

    public void addData(ArrayList<String[]> data) {
        this.data = data;
        createDataTable();
    }

    private void newRow() {
        tableRow = new TableRow(context);
    }

    private void newCell() {
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(12);
        txtCell.setTextColor(context.getResources().getColor(android.R.color.white));
    }
    private void newCellH() {
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(14);
        txtCell.setBackgroundColor(context.getResources().getColor(android.R.color.black));
        txtCell.setTextColor(context.getResources().getColor(android.R.color.white));
    }

    private void createHeader() {
        indexColumn = 0;
        newRow();
        while (indexColumn < header.length) {
            newCellH();
            txtCell.setText(header[indexColumn]);
            tableRow.addView(txtCell, newTableRowParamas());
            indexColumn++;
        }
        tableLayout.addView(tableRow);
    }

    public void addItems(String[] item) {
        /*
            Es importante tener en cuanta que si se usa y no se ha creado la tabla con el metodo create table
            este metodo no va a funcionar y tendremos problemas de visualizacion nos saca de la app.
            Por tanto toca crear la tabla antes de usar este metodo y luego usar este metodo.
            
         */
        Log.d("TableDynamic", "addItems: " + item.length);
        data.add(item);
        indexColumn = 0;
        newRow();
        while (indexColumn < header.length) {
            newCell();
            String info = (indexColumn < item.length) ? item[indexColumn] : "";
            txtCell.setText(info);
            tableRow.addView(txtCell, newTableRowParamas());
            indexColumn++;
        }
        tableLayout.addView(tableRow);
    }

    private void createDataTable() {
        String info;
        for (indexRow = 0; indexRow < data.size(); indexRow++) {
            newRow();
            for (indexColumn = 0; indexColumn < header.length; indexColumn++) {
                newCell();
                String[] row = data.get(indexRow);
                info = (indexColumn < row.length) ? row[indexColumn] : "";
                txtCell.setText(info);
                tableRow.addView(txtCell, newTableRowParamas());
            }
            tableLayout.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTableRowParamas() {
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1, 1, 1, 1);
        params.gravity = Gravity.CENTER;
        params.weight = 1;
        return params;
    }

    private TableRow getRow(int index) {
        return (TableRow) tableLayout.getChildAt(index);
    }

    public void linearColor() {
        indexRow = 0;
        while (indexRow < data.size()) {
            getRow(indexRow).setBackgroundColor(context.getResources().getColor(android.R.color.black));
            indexRow++;
        }

    }
    public int sizeData() {
        return data.size();
    }

}

