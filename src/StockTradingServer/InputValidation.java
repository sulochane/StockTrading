package StockTradingServer;

import StockTradingCommon.Enumeration;

public class InputValidation {

	public Validator validateString(String str, String label) {
		int stdStrLen = Enumeration.BrokerageFirm.BROKERAGE_FIRM_STRING_LENGTH;
		String delimiter = "\n";

		Validator v = new Validator();
		v.setVerified(true);
		v.setStatus("");

		if (str == null ||  str.isEmpty()) {
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

}
