package StockTradingServer;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import StockTradingCommon.Enumeration;

public class InputValidation {

	public Validator validateString(String str, String label) {
		int stdStrLen = Enumeration.BrokerageFirm.BROKERAGE_FIRM_STRING_LENGTH;
		String delimiter = "\n";

		Validator v = new Validator();
		v.setVerified(true);
		v.setStatus("");

		if (str.isEmpty()) {
			v.setVerified(false);
			v.setStatus(label + " field cannot be empty" + delimiter);
		} else if (str.length() > stdStrLen) {
			v.setVerified(false);
			v.setStatus(label + " length cannot be longer than " + stdStrLen
					+ " characters" + delimiter);
		}

		return v;
	}

	public Validator validateInt(int input, String label) {
		int statusActive = Enumeration.BrokerageFirm.BROKERAGE_FIRM_STATUS_ACTIVE_ID;
		int statusInactive = Enumeration.BrokerageFirm.BROKERAGE_FIRM_STATUS_INACTIVE_ID;
		String delimiter = "\n";

		Validator v = new Validator();
		v.setVerified(true);
		v.setStatus("");

		if (input != statusActive && input != statusInactive) {
			v.setVerified(false);
			v.setStatus(label
					+ " has illegal value. Something nasty is happening."
					+ delimiter);
		}

		return v;
	}

	public Validator validateIntGeneral(int input, String label) {
		String delimiter = "\n";
		Validator v = new Validator();

		v.setVerified(true);
		v.setStatus("");

		if (input < 1) {
			v.setVerified(false);
			v.setStatus("Error " + label
					+ " is not a proper int. Error happened." + delimiter);
		}

		return v;
	}

	public Validator validateDoubleGeneral(double input, String label) {
		String delimiter = "\n";
		Validator v = new Validator();

		v.setVerified(true);
		v.setStatus("");

		if (input <= 0) {
			v.setVerified(false);
			v.setStatus("Error " + label
					+ " is not a proper double. Error happened." + delimiter);
		}

		return v;
	}

	public Validator validateTimestampGeneral(Timestamp input, String label) {
		String delimiter = "\n";
		Validator v = new Validator();

		v.setVerified(true);
		v.setStatus("");

		if (input == null) {
			v.setVerified(false);
			v.setStatus("Error " + label + " is not a proper date." + delimiter);
		}

		return v;
	}

	public Validator validateEmail(String str, String label) {
		String delimiter = "\n";
		Validator v = new Validator();

		Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(str);

		v.setVerified(false);
		v.setStatus(label + " is not an email of proper form" + delimiter);

		if (matcher.matches()) {
			v.setVerified(true);
			v.setStatus("");
		}

		return v;
	}

}
