package org.molina.calculator20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    //Botones de operaciones.
    private Button btn_sum;
    private Button btn_subtraction;
    private Button btn_multiplication;
    private Button btn_division;
    private Button btn_negative;
    private Button btn_dot;
    private Button btn_equals;
    private Button btn_equals2;

    //Botones de borrado y reinicio.
    private Button btn_delete;
    private Button btn_ac;

    //Botones 0-9.
    private Button bnt_one;
    private Button bnt_two;
    private Button bnt_three;
    private Button bnt_four;
    private Button bnt_five;
    private Button bnt_six;
    private Button bnt_seven;
    private Button bnt_eight;
    private Button bnt_nine;
    private Button bnt_zero;

    private EditText et_number;

    //Variable que almacena los numeros con los que se operaran.
    private String txtNumber;

    //Variable que comprueba la ultima operacion presionada.
    //-1 -> No operation pressed
    //0 -> Equal
    //1 -> Sum
    //2 -> Subtraction
    //3 -> Multiplication
    //4 -> Division
    private int lastOperationPressed;

    //Variable que comprueba si el numero tiene ya una coma.
    private boolean hasDot;

    //Variable que comprueba si se ha entrado en una funcion.
    private boolean hasEnteredFunction;

    //Variable que comprueba si se ha dividido entre cero.
    private boolean dividedByZero;

    //Variable que almacena el resultado de las operaciones.
    private double num;

    private TextView testResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //Inicializacion de variables
        testResult = (TextView)findViewById(R.id.test);

        et_number = (EditText)findViewById(R.id.etNumber);

        bnt_one = (Button)findViewById(R.id.btnOne);
        bnt_two = (Button)findViewById(R.id.btnTwo);
        bnt_three = (Button)findViewById(R.id.btnThree);
        bnt_four = (Button)findViewById(R.id.btnFour);
        bnt_five = (Button)findViewById(R.id.btnFive);
        bnt_six = (Button)findViewById(R.id.btnSix);
        bnt_seven = (Button)findViewById(R.id.btnSeven);
        bnt_eight = (Button)findViewById(R.id.btnEight);
        bnt_nine = (Button)findViewById(R.id.btnNine);
        bnt_zero = (Button)findViewById(R.id.btnZero);

        btn_delete = (Button)findViewById(R.id.btnDelete);
        btn_ac = (Button)findViewById(R.id.btnAc);

        btn_sum = (Button)findViewById(R.id.sum);
        btn_subtraction = (Button)findViewById(R.id.subtraction);
        btn_multiplication = (Button)findViewById(R.id.multiplication);
        btn_division = (Button)findViewById(R.id.division);
        btn_negative = (Button)findViewById(R.id.btnNegative);
        btn_dot = (Button)findViewById(R.id.btnDot);
        btn_equals = (Button)findViewById(R.id.btnEquals);
        btn_equals2 = (Button)findViewById(R.id.btnEquals2);

        num = 0;
        txtNumber = "";
        lastOperationPressed = -1;
        hasDot = false;
        hasEnteredFunction = false;
        dividedByZero = false;

        //Botones 0-9
        bnt_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionCheck();
                txtNumber += 1;
                et_number.setText(txtNumber);
            }
        });
        bnt_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionCheck();
                txtNumber += 2;
                et_number.setText(txtNumber);
            }
        });
        bnt_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionCheck();
                txtNumber += 3;
                et_number.setText(txtNumber);
            }
        });
        bnt_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionCheck();
                txtNumber += 4;
                et_number.setText(txtNumber);
            }
        });
        bnt_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionCheck();
                txtNumber += 5;
                et_number.setText(txtNumber);
            }
        });
        bnt_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionCheck();
                txtNumber += 6;
                et_number.setText(txtNumber);
            }
        });
        bnt_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionCheck();
                txtNumber += 7;
                et_number.setText(txtNumber);
            }
        });
        bnt_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionCheck();
                txtNumber += 8;
                et_number.setText(txtNumber);
            }
        });
        bnt_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionCheck();
                txtNumber += 9;
                et_number.setText(txtNumber);
            }
        });
        bnt_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionCheck();
                txtNumber += 0;
                et_number.setText(txtNumber);
            }
        });

        //Boton de negacion, cambia el signo del numero
        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionCheck();
                //Si el campo de texto no esta vacio.
                if (txtNumber.length() > 0){
                    //Si el primer caracter del string es "-", lo quita y si no lo es lo añade.
                    if (String.valueOf(txtNumber.charAt(0)).equals("-")){
                        txtNumber = txtNumber.replace("-","");
                    }else {
                        txtNumber = "-" + txtNumber;
                    }
                //Si el campo de texto esta vacio añade "-".
                }else {
                    txtNumber = "-";
                }
                //Muestra en el campo de texto el valor actual de txtNumber.
                et_number.setText(txtNumber);
            }
        });

        //Boton que añade una punto al numero o si no hay ningun numero añade "0.".
        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionCheck();
                //Si el campo de texto no esta vacio.
                if (txtNumber.length() > 0){
                    //Comprueba que la string no contenga ningun punto.
                    for (int i = 0; i < txtNumber.length(); i++) {
                        if (txtNumber.charAt(i) == '.'){
                            hasDot = true;
                        }
                    }
                    //Si la string no contiene ningun punto se lo añade.
                    if (!hasDot){
                        txtNumber += ".";
                        hasDot = true;
                    }
                }else {
                    txtNumber = "0.";
                }
                //Muestra en el campo de texto el valor actual de txtNumber.
                et_number.setText(txtNumber);
            }
        });

        //Boton que borra el ultimo caracter del string.
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si el campo de texto no esta vacio
                if (txtNumber.length() > 0){
                    //Comprueba si el caracer que ha borrado es un punto para cambiar
                    //hasdot a false.
                    if (txtNumber.charAt(txtNumber.length()-1) == '.'){
                        hasDot = false;
                    }
                    //Borra el ultimo caracter del string.
                    txtNumber = txtNumber.substring(0, txtNumber.length()-1);
                    //Muestra en el campo de texto el valor actual de txtNumber.
                    et_number.setText(txtNumber);
                }
            }
        });

        //Boton que resetea la calculadora.
        btn_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumber = "";
                et_number.setText(txtNumber);
                num = 0;
                lastOperationPressed = -1;
                hasDot = false;
                hasEnteredFunction = false;
                dividedByZero = false;
                testResult.setText(String.valueOf(num));
            }
        });

        //Boton que suma dos o mas numeros.
        btn_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si el campo de texto no esta vacio.
                if (!txtNumber.equals("")){
                    //Si la ultima operacion presionada no fue "equals".
                    if (lastOperationPressed != 0){
                        //Si la ultima operacion presionada fue "sum" o "none" suma a num
                        //el valor del numero en el campo de texto.
                        if (lastOperationPressed == 1 || lastOperationPressed == -1){
                            num += (Double.valueOf(txtNumber));
                            txtNumber = "";
                            et_number.setText(txtNumber);
                            testResult.setText(String.valueOf(num));
                        //Sino entra en la funcion lastOperation().
                        }else {
                            lastOperation(lastOperationPressed);
                        }
                    //Sino borra el numero actual del campo de texto.
                    }else {
                        txtNumber = "";
                        et_number.setText(txtNumber);
                    }
                    //Da el valor 1(sum) a lastOperationPressed.
                    lastOperationPressed = 1;
                }
            }
        });

        //Boton que resta dos o mas numeros.
        btn_subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si el campo de texto no esta vacio.
                if (!txtNumber.equals("")){
                    //Si la ultima operacion presionada no fue "equals".
                    if (lastOperationPressed != 0){
                        //Si la ultima operacion presionada fue "subtraction" resta a
                        //num el valor del numero en el campo de texto.
                        if (lastOperationPressed == 2){
                            num -= (Double.valueOf(txtNumber));
                            txtNumber = "";
                            et_number.setText(txtNumber);
                            testResult.setText(String.valueOf(num));
                        //Si la ultima operacion presionada fue "none" suma a num el
                        //valor del numero en el campo de texto pues para poder restar
                        //dos numeros el primer numero debe de ser sumado a num cuyo valor
                        //al principio es 0.
                        }else if (lastOperationPressed == -1){
                            num += (Double.valueOf(txtNumber));
                            txtNumber = "";
                            et_number.setText(txtNumber);
                            testResult.setText(String.valueOf(num));
                        //Sino entra en la funcion lastOperation().
                        }else {
                            lastOperation(lastOperationPressed);
                        }
                    //Sino borra el numero actual del campo de texto.
                    }else {
                        txtNumber = "";
                        et_number.setText(txtNumber);
                    }
                    //Da el valor 2(subtraction) a lastOperationPressed.
                    lastOperationPressed = 2;
                }
            }
        });

        //Boton que multiplica dos o mas numeros.
        btn_multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si el campo de texto esta vacio.
                if (!txtNumber.equals("")){
                    //Si la ultima operacion presionada no fue "equals".
                    if (lastOperationPressed != 0){
                        //Si la ultima operacion presionada fue "multiplication" multiplica
                        //num por el valor del numero en el campo de texto.
                        if (lastOperationPressed == 3){
                            num *= (Double.valueOf(txtNumber));
                            txtNumber = "";
                            et_number.setText(txtNumber);
                            testResult.setText(String.valueOf(num));
                        //Si la ultima operacion presionada fue "none" suma a num el
                        //valor del numero en el campo de texto pues para poder multiplicar
                        //dos numeros el primer numero debe de ser sumado a num cuyo valor
                        //al principio es 0.
                        }else if (lastOperationPressed == -1){
                            num += (Double.valueOf(txtNumber));
                            txtNumber = "";
                            et_number.setText(txtNumber);
                            testResult.setText(String.valueOf(num));
                        //Sino entra en la funcion lastOperation().
                        }else {
                            lastOperation(lastOperationPressed);
                        }
                    //Sino borra el numero actual del campo de texto.
                    }else {
                        txtNumber = "";
                        et_number.setText(txtNumber);
                    }
                    //Da el valor 3(multiplication) a lastOperationPressed.
                    lastOperationPressed = 3;
                }
            }
        });

        //Boton que divide dos o mas numeros.
        btn_division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si el campo de texto esta vacio
                if (!txtNumber.equals("")){
                    //Si la ultima operacion presionada no fue "equals".
                    if (lastOperationPressed != 0){
                        //Si la ultima operacion presionada fue "division".
                        if (lastOperationPressed == 4){
                            //Si el numero en el campo de texto no es 0 divide num
                            //entre el valor del numero en el campo de texto.
                            if (Double.valueOf(txtNumber) != 0){
                                num /= (Double.valueOf(txtNumber));
                                txtNumber = "";
                                et_number.setText(txtNumber);
                                testResult.setText(String.valueOf(num));
                            //Sino para evitar el resultado Infinity al dividir entre 0, da el valor 0 a
                            //num y vacia el campo de texto y el valor 1(none) a lastOperationPressed.
                            }else {
                                txtNumber = "";
                                et_number.setText(txtNumber);
                                num = 0;
                                hasDot = false;
                                dividedByZero = true;
                            }
                        //Si la ultima operacion presionada fue "none" suma a num el
                        //valor del numero en el campo de texto pues para poder dividir
                        //dos numeros el primer numero debe de ser sumado a num cuyo valor
                        //al principio es 0.
                        }else if (lastOperationPressed == -1){
                            num += (Double.valueOf(txtNumber));
                            txtNumber = "";
                            et_number.setText(txtNumber);
                            testResult.setText(String.valueOf(num));
                        //Sino entra en la funcion lastOperation().
                        }else {
                            lastOperation(lastOperationPressed);
                        }
                    //Sino borra el numero actual del campo de texto.
                    }else {
                        if (dividedByZero){
                            num+= (Double.valueOf(txtNumber));
                            dividedByZero = false;
                        }
                        txtNumber = "";
                        et_number.setText(txtNumber);
                    }
                    //Da el valor 4(division) a lastOperationPressed.
                    lastOperationPressed = 4;
                }
            }
        });

        //Boton que en funcion de la ultima operacion presionada, da un resulto
        //a dicha operacion.
        btn_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si el campo de texto no esta vacio
                if (!txtNumber.equals("")){
                    //Si la ultima operacion presionada no fue "equals"
                    //entra en la funcion lastOperation().
                    if (lastOperationPressed != 0){
                        lastOperation(lastOperationPressed);
                        lastOperationPressed = 0;
                    }
                }
            }
        });

        //Boton temporal hace lo mismo que btn_equals.
        btn_equals2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNumber.equals("")){
                    if (lastOperationPressed != 0){
                        lastOperation(lastOperationPressed);
                        lastOperationPressed = 0;
                    }
                }
            }
        });

    }

    //Funcion que comprueba si se ha entrado a la funcion lastOperation al
    //pulsar un boton, en cuyo caso se vacia txtNumber y se da el valor
    //true a hasEnteredFunction.
    private void functionCheck() {
        if (hasEnteredFunction){
            txtNumber = "";
            hasEnteredFunction = false;
        }
    }

    //Funcion que comprueba la ultima operacion presionada y en funcion de
    //cual fuera hace una operacion distinta:
    //case 1(sum) suma a num el valor del numero en el campo de texto.
    //case 2(subtraction) resta a num el valor del numero en el campo de texto.
    //case 3(multiplication) multiplica num por el valor del numero en el campo de texto.
    //case 4(division) divide num entre el valor del numero en el campo de texto comprobando
    //que no se divida entre 0.
    private void lastOperation(int lastOperationPressed){
        switch (lastOperationPressed){
                case 1:
                    num+= Double.valueOf(txtNumber);
                    break;
                case 2:
                    num-= Double.valueOf(txtNumber);
                    break;
                case 3:
                    num*= Double.valueOf(txtNumber);
                    break;
                case 4:
                    if (Double.valueOf(txtNumber) != 0) {
                        num /= Double.valueOf(txtNumber);
                    }else {
                        txtNumber = "";
                        et_number.setText(txtNumber);
                        num = 0;
                        lastOperationPressed = -1;
                        hasDot = false;
                        hasEnteredFunction = false;
                        dividedByZero = true;
                        testResult.setText(String.valueOf(num));
                    }
                    break;
        }
        hasEnteredFunction = true;
        testResult.setText(String.valueOf(num));
        txtNumber = String.valueOf(num);
        et_number.setText(String.valueOf(txtNumber));
    }
}
