public sealed class Attribute permits Artifact {
	private static final double[] HP_FLAT_VALUE = { 209.13, 239.00, 268.88, 298.75 };
	private static final double[] ATK_FLAT_VALUE = { 13.62, 15.56, 17.51, 19.45 };
	private static final double[] DEF_FLAT_VALUE = { 16.20, 18.52, 20.83, 23.15 };
	private static final double[] HP_PER_VALUE = { 4.08, 4.66, 5.25, 5.83 };
	private static final double[] ATK_PER_VALUE = { 4.08, 4.66, 5.25, 5.83 };
	private static final double[] DEF_PER_VALUE = { 5.10, 5.83, 6.56, 7.29 };
	private static final double[] ENERGY_RECHARGE_VALUE = { 4.53, 5.18, 5.83, 6.48 };
	private static final double[] ELEMENTAL_MASTERY_VALUE = { 16.32, 18.65, 20.98, 23.31 };
	private static final double[] CRIT_RATE_VALUE = { 2.72, 3.11, 3.50, 3.89 };
	private static final double[] CRIT_DMG_VALUE = { 5.44, 6.22, 6.99, 7.77 };
	
	public static final String HP_FLAT = "HP";
	public static final String ATK_FLAT = "ATK";
	public static final String DEF_FLAT = "DEF";
	public static final String HP_PER = "HP%";
	public static final String ATK_PER = "ATK%";
	public static final String DEF_PER = "DEF%";
	public static final String ENERGY_RECHARGE = "Energy Recharge%";
	public static final String ELEMENTAL_MASTERY = "Elemental Mastery";
	public static final String CRIT_RATE = "CRIT Rate%";
	public static final String CRIT_DMG = "CRIT DMG%";
	public static final String PYRO_DMG_BONUS = "Pyro DMG Bonus%";
	public static final String ELECTRO_DMG_BONUS = "Electro DMG Bonus%";
	public static final String CRYO_DMG_BONUS = "Cryo DMG Bonus%";
	public static final String HYDRO_DMG_BONUS = "Hydro DMG Bonus%";
	public static final String DENDRO_DMG_BONUS = "Dendro DMG Bonus%";
	public static final String ANEMO_DMG_BONUS = "Anemo DMG Bonus%";
	public static final String GEO_DMG_BONUS = "Geo DMG Bonus%";
	public static final String PHYSICAL_DMG_BONUS = "Physical DMG Bonus%";
	public static final String HEALING_BONUS = "Healing Bonus%";
	
	public static final Stat[] STATS = {
			new Stat(HP_FLAT, HP_FLAT_VALUE),
			new Stat(ATK_FLAT, ATK_FLAT_VALUE),
			new Stat(DEF_FLAT, DEF_FLAT_VALUE),
			new Stat(HP_PER, HP_PER_VALUE),
			new Stat(ATK_PER, ATK_PER_VALUE),
			new Stat(DEF_PER, DEF_PER_VALUE),
			new Stat(ENERGY_RECHARGE, ENERGY_RECHARGE_VALUE),
			new Stat(ELEMENTAL_MASTERY, ELEMENTAL_MASTERY_VALUE),
			new Stat(CRIT_RATE, CRIT_RATE_VALUE),
			new Stat(CRIT_DMG, CRIT_DMG_VALUE)
	};
	public static final String[] ATTRIBUTES = { 
			HP_FLAT, ATK_FLAT, DEF_FLAT, 
			HP_PER, ATK_PER, DEF_PER, 
			ENERGY_RECHARGE, ELEMENTAL_MASTERY,
			CRIT_RATE, CRIT_DMG 
	};
	public static final String[] SPECIAL_ATTRIBUTE = {
			PYRO_DMG_BONUS, ELECTRO_DMG_BONUS, CRYO_DMG_BONUS,
			HYDRO_DMG_BONUS, DENDRO_DMG_BONUS, ANEMO_DMG_BONUS,
			GEO_DMG_BONUS, PHYSICAL_DMG_BONUS, HEALING_BONUS 
	};
	public static final String[] PERCENTAGE_ATTRIBUTE = { 
			"HP%", "ATK%", "DEF%", 
			"Energy Recharge%", "CRIT Rate%", "CRIT DMG%" 
	};
	
	public boolean isNotSpecial(String attribute) {
		for(String attributes : ATTRIBUTES) {
			if(attributes.equals(attribute)) {
				return true;
			}
		}
		return false;
	}
	
	public Double[] getHpPer() {
		Double[] temp = new Double[4];
		for(int i = 0; i < HP_PER_VALUE.length; i++) {
			temp[i] = HP_PER_VALUE[i];
		}
		return temp;
	}

	public Double[] getAtkPer() {
		Double[] temp = new Double[4];
		for(int i = 0; i < ATK_PER_VALUE.length; i++) {
			temp[i] = ATK_PER_VALUE[i];
		}
		return temp;
	}

	public Double[] getDefPer() {
		Double[] temp = new Double[4];
		for(int i = 0; i < DEF_PER_VALUE.length; i++) {
			temp[i] = DEF_PER_VALUE[i];
		}
		return temp;
	}

	public Double[] getHpFlat() {
		Double[] temp = new Double[4];
		for(int i = 0; i < HP_FLAT_VALUE.length; i++) {
			temp[i] = HP_FLAT_VALUE[i];
		}
		return temp;
	}

	public Double[] getAtkFlat() {
		Double[] temp = new Double[4];
		for(int i = 0; i < ATK_FLAT_VALUE.length; i++) {
			temp[i] = ATK_FLAT_VALUE[i];
		}
		return temp;
	}

	public Double[] getDefFlat() {
		Double[] temp = new Double[4];
		for(int i = 0; i < DEF_FLAT_VALUE.length; i++) {
			temp[i] = DEF_FLAT_VALUE[i];
		}
		return temp;
	}

	public Double[] getEnergyRecharge() {
		Double[] temp = new Double[4];
		for(int i = 0; i < ENERGY_RECHARGE_VALUE.length; i++) {
			temp[i] = ENERGY_RECHARGE_VALUE[i];
		}
		return temp;
	}

	public Double[] getElementalMastery() {
		Double[] temp = new Double[4];
		for(int i = 0; i < ELEMENTAL_MASTERY_VALUE.length; i++) {
			temp[i] = ELEMENTAL_MASTERY_VALUE[i];
		}
		return temp;
	}

	public Double[] getCritRate() {
		Double[] temp = new Double[4];
		for(int i = 0; i < CRIT_RATE_VALUE.length; i++) {
			temp[i] = CRIT_RATE_VALUE[i];
		}
		return temp;
	}

	public Double[] getCritDamage() {
		Double[] temp = new Double[4];
		for(int i = 0; i < CRIT_DMG_VALUE.length; i++) {
			temp[i] = CRIT_DMG_VALUE[i];
		}
		return temp;
	}
}
