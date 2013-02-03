package com.gayashanna.solud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/***
 * This class is used to take the equations as user inputs. The user may type
 * the equations with either the custom made user friendly key pad or the
 * built-in keypad of the device.
 * 
 * @author GayashanNA
 * 
 */
public class TextInputActivity extends Activity implements OnClickListener {

	private Button bSolve, bClear, bDel;
	private Button[] buttons;
	private EditText etEquation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_input); // view text_input layout
		initialize();
	}

	/***
	 * initializing the local variables.
	 */
	private void initialize() {
		// disable the soft text keyboard from viewing
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		bSolve = (Button) findViewById(R.id.bSolve);
		bClear = (Button) findViewById(R.id.bClear);
		bDel = (Button) findViewById(R.id.bBackspace);
		etEquation = (EditText) findViewById(R.id.etInsertEquation);
		bClear.setOnClickListener(this);
		bSolve.setOnClickListener(this);
		bDel.setOnClickListener(this);
		// acquire the bundle containing the equation sent by previous activity
		Bundle extras = getIntent().getExtras();
		// if an equation is send to the activity show that in the etEquation
		// EditText view
		if (extras != null) {
			etEquation.setText(extras.getString("Equation"));
		}
		// manually initializing the easy-to-type-in-equations keys
		buttons = new Button[35];
		buttons[0] = (Button) findViewById(R.id.bZero);
		buttons[1] = (Button) findViewById(R.id.bOne);
		buttons[2] = (Button) findViewById(R.id.bTwo);
		buttons[3] = (Button) findViewById(R.id.bThree);
		buttons[4] = (Button) findViewById(R.id.bFour);
		buttons[5] = (Button) findViewById(R.id.bFive);
		buttons[6] = (Button) findViewById(R.id.bSix);
		buttons[7] = (Button) findViewById(R.id.bSeven);
		buttons[8] = (Button) findViewById(R.id.bEight);
		buttons[9] = (Button) findViewById(R.id.bNine);
		buttons[10] = (Button) findViewById(R.id.bY);
		buttons[11] = (Button) findViewById(R.id.bYFirstOrder);
		buttons[12] = (Button) findViewById(R.id.bYSecondOrder);
		buttons[13] = (Button) findViewById(R.id.bX);
		buttons[14] = (Button) findViewById(R.id.bXFirstOrder);
		buttons[15] = (Button) findViewById(R.id.bXSecondOrder);
		buttons[16] = (Button) findViewById(R.id.bAdd);
		buttons[17] = (Button) findViewById(R.id.bSubtract);
		buttons[18] = (Button) findViewById(R.id.bEquals);
		buttons[19] = (Button) findViewById(R.id.bOpenPara);
		buttons[20] = (Button) findViewById(R.id.bClosePara);
		buttons[21] = (Button) findViewById(R.id.bToThePower);
		buttons[22] = (Button) findViewById(R.id.bSqrt);
		buttons[23] = (Button) findViewById(R.id.bEToThePower);
		buttons[24] = (Button) findViewById(R.id.bSin);
		buttons[25] = (Button) findViewById(R.id.bCos);
		buttons[26] = (Button) findViewById(R.id.bTan);
		buttons[27] = (Button) findViewById(R.id.bT);
		buttons[28] = (Button) findViewById(R.id.bSquared);
		buttons[29] = (Button) findViewById(R.id.bOrder);
		buttons[30] = (Button) findViewById(R.id.bDivide);
		buttons[31] = (Button) findViewById(R.id.bMultiply);
		buttons[32] = (Button) findViewById(R.id.bComma);
		buttons[33] = (Button) findViewById(R.id.bXThirdOrder);
		buttons[34] = (Button) findViewById(R.id.bYThirdOrder);
		// set on click listeners for the buttons
		setOnClickListnersForButtonsArray();
	}

	/**
	 * initialize the buttons array with the onClickListners.
	 */
	private void setOnClickListnersForButtonsArray() {
		for (int i = 0; i < 35; i++) {
			buttons[i].setOnClickListener(this);
		}

	}

	/***
	 * defines what the buttons do when clicked.
	 */
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bClear:
			etEquation.setText(null);
			break;
		case R.id.bSolve:
			if (checkInput()) {
				// create a new bundle
				Bundle equationBundle = new Bundle();
				// put the equation into the bundle
				equationBundle.putString("Equation", etEquation.getText()
						.toString());
				// display the solution
				displaySolution(equationBundle);
			} else {
				Toast.makeText(this,
						"Please enter a differential equation to solve.", 10)
						.show();
			}
			break;

		case R.id.bBackspace:
			deleteText();
			break;
		case R.id.bZero:
			etEquation.getText().insert(etEquation.getSelectionStart(), "0");
			break;
		case R.id.bOne:
			etEquation.getText().insert(etEquation.getSelectionStart(), "1");
			break;
		case R.id.bTwo:
			etEquation.getText().insert(etEquation.getSelectionStart(), "2");
			break;
		case R.id.bThree:
			etEquation.getText().insert(etEquation.getSelectionStart(), "3");
			break;
		case R.id.bFour:
			etEquation.getText().insert(etEquation.getSelectionStart(), "4");
			break;
		case R.id.bFive:
			etEquation.getText().insert(etEquation.getSelectionStart(), "5");
			break;
		case R.id.bSix:
			etEquation.getText().insert(etEquation.getSelectionStart(), "6");
			break;
		case R.id.bSeven:
			etEquation.getText().insert(etEquation.getSelectionStart(), "7");
			break;
		case R.id.bEight:
			etEquation.getText().insert(etEquation.getSelectionStart(), "8");
			break;
		case R.id.bNine:
			etEquation.getText().insert(etEquation.getSelectionStart(), "9");
			break;
		case R.id.bAdd:
			etEquation.getText().insert(etEquation.getSelectionStart(), "+");
			break;
		case R.id.bSubtract:
			etEquation.getText().insert(etEquation.getSelectionStart(), "-");
			break;
		case R.id.bY:
			etEquation.getText().insert(etEquation.getSelectionStart(), "y(t)");
			break;
		case R.id.bYFirstOrder:
			etEquation.getText().insert(etEquation.getSelectionStart(),
					"y\'(t)");
			break;
		case R.id.bYSecondOrder:
			etEquation.getText().insert(etEquation.getSelectionStart(),
					"y\'\'(t)");
			break;
		case R.id.bX:
			etEquation.getText().insert(etEquation.getSelectionStart(), "x(t)");
			break;
		case R.id.bXFirstOrder:
			etEquation.getText().insert(etEquation.getSelectionStart(),
					"x\'(t)");
			break;
		case R.id.bXSecondOrder:
			etEquation.getText().insert(etEquation.getSelectionStart(),
					"x\'\'(t)");
			break;
		case R.id.bEquals:
			etEquation.getText().insert(etEquation.getSelectionStart(), "=");
			break;
		case R.id.bOpenPara:
			etEquation.getText().insert(etEquation.getSelectionStart(), "(");
			break;
		case R.id.bClosePara:
			etEquation.getText().insert(etEquation.getSelectionStart(), ")");
			break;
		case R.id.bToThePower:
			etEquation.getText().insert(etEquation.getSelectionStart(), "^(");
			break;
		case R.id.bSqrt:
			etEquation.getText()
					.insert(etEquation.getSelectionStart(), "sqrt(");
			break;
		case R.id.bEToThePower:
			etEquation.getText().insert(etEquation.getSelectionStart(), "e^(");
			break;
		case R.id.bSin:
			etEquation.getText().insert(etEquation.getSelectionStart(), "sin(");
			break;
		case R.id.bCos:
			etEquation.getText().insert(etEquation.getSelectionStart(), "cos(");
			break;
		case R.id.bTan:
			etEquation.getText().insert(etEquation.getSelectionStart(), "tan(");
			break;
		case R.id.bT:
			etEquation.getText().insert(etEquation.getSelectionStart(), "t");
			break;
		case R.id.bSquared:
			etEquation.getText().insert(etEquation.getSelectionStart(), "^2");
			break;
		case R.id.bOrder:
			etEquation.getText().insert(etEquation.getSelectionStart(), "\'");
			break;
		case R.id.bXThirdOrder:
			etEquation.getText().insert(etEquation.getSelectionStart(),
					"x\'\'\'(t)");
			break;
		case R.id.bYThirdOrder:
			etEquation.getText().insert(etEquation.getSelectionStart(),
					"y\'\'\'(t)");
			break;
		case R.id.bDivide:
			etEquation.getText().insert(etEquation.getSelectionStart(), "/");
			break;
		case R.id.bMultiply:
			etEquation.getText().insert(etEquation.getSelectionStart(), "*");
			break;
		case R.id.bComma:
			etEquation.getText().insert(etEquation.getSelectionStart(), ",");
			break;
		}
	}

	/***
	 * This method Validates the input to be a differential equation.
	 * 
	 * @return <code>boolean</code>
	 */
	private boolean checkInput() {
		if ("".contentEquals(etEquation.getText().toString())) {
			return false;
		} else if (!(etEquation.getText().toString().matches(".+=.+"))) {
			return false;
		}
		return true;
	}

	/**
	 * Parse the bundle to ViewSolutionActivity and display it.
	 * 
	 * @param equtaionBundle
	 */
	private void displaySolution(Bundle equtaionBundle) {
		Intent openViewSolutionActivity = new Intent(TextInputActivity.this,
				ViewSolutionActivity.class);
		openViewSolutionActivity.putExtras(equtaionBundle);
		startActivity(openViewSolutionActivity);
	}

	/**
	 * Implements backspace function to the EditText
	 */
	private void deleteText() {
		if (!("".contentEquals(etEquation.getText().toString()))
				&& etEquation.getSelectionStart() != 0) { // if the EditText
															// view is not empty
															// and the cursor is
															// not in the
															// beginning
															// position
			etEquation.getText().delete(etEquation.getSelectionStart() - 1,
					etEquation.getSelectionStart());
		}
	}

}
