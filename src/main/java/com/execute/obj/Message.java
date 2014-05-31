package com.execute.obj;


public class Message {
	/* configuration parts */
	// konfiguracija broja dogadjaja po event-u
	protected float eventPerKmAccMid;
	protected float eventPerKmAccHi;
	protected float eventPerKmDecMid;
	protected float eventPerKmDecHi;
	protected float eventPerKmCorneringMid;
	protected float eventPerKmCorneringHi;
	protected float eventPerKmSkiddingHi;
	protected float eventPerKmAbruptTurningHi;
	protected float eventPerKmAggressiveLaneChangeMid;
	protected float eventPerKmAggressiveLaneChangeHi;
	protected float eventPerKmBarrierAvoidanceMid;
	protected float eventPerKmBarrierAvoidanceHi;
	protected float eventPerKmSpinningHi;
	protected float eventPerKmSpeedLimit;
	// prekoracenja dogadjaja po kilometru [prema grupi dogadjaja]
	protected float boundaryOfEvents;
	
	// ukupan broj dogadjaja i broj poena po dogadjaju
	protected float totalEventPerKm;
	// test kilometri
	protected float testingTraveledDistance;
	// osnova u odnosu na koju se porede dogadjaji [na 1000km samo 1 acc_hi]
	protected float kmBase;
	// klasifikacija kilometraze u km
	protected int totalDistanceZeroKm;
	protected int totalDistanceMidKm;
	protected int totalDistanceAverageKm;
	protected int totalDistanceHiKm;
	// bonus poeni
	protected int minBonusPoints;

	// limit negativnih poena, broj maksimalno dozvoljenih eventova
	protected int limit;
	protected int maxEvent;
    
    
    
    // sume koje dobijam iz baze podataka
    protected float totalTraveledDistance;
    protected int totalEvents;
    protected int driverId;
    protected int numMidAcc;
    protected int numHiAcc;
    protected int numMidDec;
    protected int numHiDec;
    protected int numMidCornering;
    protected int numHiCornering;
    protected int numHiSkidding;
    protected int numHiAbruptTurning;
    protected int numMidAggressiveLaneChange;
    protected int numHiAggressiveLaneChange;
    protected int numMidBarrierAvoidance;
    protected int numHiBarrierAvoidance;
    protected int numHiSpinning;
    protected int numSpeedLimit;
    
    
    // sume negativnih bodova
    protected float total;
    protected float partAbruptTurningHi;
    protected float partAccHi;
    protected float partAccMid;
    protected float partAggressiveLaneChangeHi;
    protected float partAggressiveLaneChangeMid;
    protected float partBarrierAvoidanceHi;
    protected float partBarrierAvoidanceMid;
    protected float partCorneringHi;
    protected float partCorneringMid;
    protected float partDecHi;
    protected float partDecMid;
    protected float partSkiddingHi;
    protected float partSpeedLimit;
    protected float partSpinningHi;
    
    // rezultati
    String report;
    /**
     * none - no set type of driver
     * ok - safe group
     * border - drivers at the border risk
     * risk - risk group of drivers
     * agresive - agresive driver
     * extreme - too agresive driver
     */
    String typeOfDriver;
            
    public Message() {
    	
    	//@todo napuni config promenljive iz nekog config fajla osnova je 1000 km
    	
    	this.totalEventPerKm = 150;
    	this.eventPerKmAbruptTurningHi = 1;
    	this.eventPerKmAccHi = 10;
    	this.eventPerKmAccMid = 15;
    	this.eventPerKmAggressiveLaneChangeHi = 5;
    	this.eventPerKmAggressiveLaneChangeMid = 1;
    	this.eventPerKmBarrierAvoidanceHi = 1;
    	this.eventPerKmBarrierAvoidanceMid = 1;
    	this.eventPerKmCorneringHi = 7;
    	this.eventPerKmCorneringMid = 35;
    	this.eventPerKmDecHi = 10;
    	this.eventPerKmDecMid = 15;
    	this.eventPerKmSkiddingHi = 1;
    	this.eventPerKmSpeedLimit = 46;
    	this.eventPerKmSpinningHi = 0;
    	this.testingTraveledDistance = 10;
    	this.kmBase = 1000;

    	this.totalDistanceZeroKm = 0;
    	this.totalDistanceMidKm = 1000;
    	this.totalDistanceAverageKm = 2500;
    	this.totalDistanceHiKm = 5000;
    	
    	this.minBonusPoints = 2;

    	this.total = 0;
    	this.partAbruptTurningHi = Integer.MIN_VALUE;
    	this.partAccHi = Integer.MIN_VALUE;
    	this.partAccMid = Integer.MIN_VALUE;
    	this.partAggressiveLaneChangeHi = Integer.MIN_VALUE;
    	this.partAggressiveLaneChangeMid = Integer.MIN_VALUE;
    	this.partBarrierAvoidanceHi = Integer.MIN_VALUE;
    	this.partBarrierAvoidanceMid = Integer.MIN_VALUE;
    	this.partCorneringHi = Integer.MIN_VALUE;
    	this.partCorneringMid = Integer.MIN_VALUE;
    	this.partDecHi = Integer.MIN_VALUE;
    	this.partDecMid = Integer.MIN_VALUE;
    	this.partSkiddingHi = Integer.MIN_VALUE;
    	this.partSpeedLimit = Integer.MIN_VALUE;
    	this.partSpinningHi = Integer.MIN_VALUE;
    	
    	this.boundaryOfEvents = (float) 1.5;   
    	this.typeOfDriver = "none";
    }   
    
    public void setRestData() {
    	
    	this.numHiAbruptTurning = 1;
    	this.numHiAcc = 25;
    	this.numHiAggressiveLaneChange = 7;
    	this.numHiBarrierAvoidance = 1;
    	this.numHiCornering = 10;
    	this.numHiDec = 1;
    	this.numHiSkidding = 1;
    	this.numHiSpinning = 1;
    	this.numMidAcc = 14;
    	this.numMidAggressiveLaneChange = 1;
    	this.numMidBarrierAvoidance = 1;
    	this.numMidCornering = 40;
    	this.numMidDec = 18;
    	this.numSpeedLimit = 1;
    	this.totalTraveledDistance = 1001;
    	this.totalEvents = 160;
    	this.driverId = 1234;
    }

    
    // geteri za bazu 
	public float getTotalTraveledDistance() {
		return totalTraveledDistance;
	}
	
	public int getTotalEvents() {
		return totalEvents;
	}
	
	public int getNumMidAcc() {
		return numMidAcc;
	}

	public int getNumHiAcc() {
		return numHiAcc;
	}

	public int getNumMidDec() {
		return numMidDec;
	}

	public int getNumHiDec() {
		return numHiDec;
	}

	public int getNumMidCornering() {
		return numMidCornering;
	}

	public int getNumHiCornering() {
		return numHiCornering;
	}


	public int getNumHiSkidding() {
		return numHiSkidding;
	}

	public int getNumHiAbruptTurning() {
		return numHiAbruptTurning;
	}

	public int getNumMidAggressiveLaneChange() {
		return numMidAggressiveLaneChange;
	}

	public int getNumHiAggressiveLaneChange() {
		return numHiAggressiveLaneChange;
	}

	public int getNumMidBarrierAvoidance() {
		return numMidBarrierAvoidance;
	}

	public int getNumHiBarrierAvoidance() {
		return numHiBarrierAvoidance;
	}

	public int getNumHiSpinning() {
		return numHiSpinning;
	}

	public int getNumSpeedLimit() {
		return numSpeedLimit;
	}

	

	// seteri i geteri za izvestaj
	public void setReport(String txt) {
		this.report = txt;
	}
	
	
	public String getReport() {
		return this.report;
	}
	

	public int getLimit() {
		return limit;
	}
	

	public void setLimit(int limit) {
		this.limit = limit;
	}
	

	public int getMaxEvent() {
		return maxEvent;
	}
	
	public void setMaxEvent(int maxEvent) {
		this.maxEvent = maxEvent;
	}

	
	public float getTotal() {
		return total;
	}

	public float getPartAbruptTurningHi() {
		return partAbruptTurningHi;
	}

	public float getPartAccHi() {
		return partAccHi;
	}

	public float getPartAccMid() {
		return partAccMid;
	}

	public float getPartAggressiveLaneChangeHi() {
		return partAggressiveLaneChangeHi;
	}

	public float getPartAggressiveLaneChangeMid() {
		return partAggressiveLaneChangeMid;
	}

	public float getPartBarrierAvoidanceHi() {
		return partBarrierAvoidanceHi;
	}

	public float getPartBarrierAvoidanceMid() {
		return partBarrierAvoidanceMid;
	}

	public float getPartCorneringHi() {
		return partCorneringHi;
	}

	public float getPartCorneringMid() {
		return partCorneringMid;
	}

	public float getPartDecHi() {
		return partDecHi;
	}

	public float getPartDecMid() {
		return partDecMid;
	}

	public float getPartSkiddingHi() {
		return partSkiddingHi;
	}

	public float getPartSpeedLimit() {
		return partSpeedLimit;
	}

	public float getPartSpinningHi() {
		return partSpinningHi;
	}

	public void setTotal(float total) {
		this.total = total;
	}	

	public void setPartAbruptTurningHi(float partAbruptTurningHi) {
		this.partAbruptTurningHi = partAbruptTurningHi;
	}

	public void setPartAccHi(float partAccHi) {
		this.partAccHi = partAccHi;
	}

	public void setPartAccMid(float partAccMid) {
		this.partAccMid = partAccMid;
	}

	public void setPartAggressiveLaneChangeHi(float partAggressiveLaneChangeHi) {
		this.partAggressiveLaneChangeHi = partAggressiveLaneChangeHi;
	}

	public void setPartAggressiveLaneChangeMid(float partAggressiveLaneChangeMid) {
		this.partAggressiveLaneChangeMid = partAggressiveLaneChangeMid;
	}

	public void setPartBarrierAvoidanceHi(float partBarrierAvoidanceHi) {
		this.partBarrierAvoidanceHi = partBarrierAvoidanceHi;
	}

	public void setPartBarrierAvoidanceMid(float partBarrierAvoidanceMid) {
		this.partBarrierAvoidanceMid = partBarrierAvoidanceMid;
	}

	public void setPartCorneringHi(float partCorneringHi) {
		this.partCorneringHi = partCorneringHi;
	}

	public void setPartCorneringMid(float partCorneringMid) {
		this.partCorneringMid = partCorneringMid;
	}

	public void setPartDecHi(float partDecHi) {
		this.partDecHi = partDecHi;
	}

	public void setPartDecMid(float partDecMid) {
		this.partDecMid = partDecMid;
	}

	public void setPartSkiddingHi(float partSkiddingHi) {
		this.partSkiddingHi = partSkiddingHi;
	}

	public void setPartSpeedLimit(float partSpeedLimit) {
		this.partSpeedLimit = partSpeedLimit;
	}

	public void setPartSpinningHi(float partSpinningHi) {
		this.partSpinningHi = partSpinningHi;
	}

	public int getDriverId() {
		return driverId;
	}

	// geteri za sistemske promenljive [ config promeljive ]
	public float getKmBase() {
		return kmBase;
	}
		
	public float getTotalEventPerKm() {
		return totalEventPerKm;
	}
		
	public float getEventPerKmAccMid() {
		return eventPerKmAccMid;
	}

	public float getEventPerKmAccHi() {
		return eventPerKmAccHi;
	}

	public float getEventPerKmDecMid() {
		return eventPerKmDecMid;
	}

	public float getEventPerKmDecHi() {
		return eventPerKmDecHi;
	}

	public float getEventPerKmCorneringMid() {
		return eventPerKmCorneringMid;
	}

	public float getEventPerKmCorneringHi() {
		return eventPerKmCorneringHi;
	}

	public float getEventPerKmSkiddingHi() {
		return eventPerKmSkiddingHi;
	}

	public float getEventPerKmAbruptTurningHi() {
		return eventPerKmAbruptTurningHi;
	}

	public float getEventPerKmAggressiveLaneChangeMid() {
		return eventPerKmAggressiveLaneChangeMid;
	}

	public float getEventPerKmAggressiveLaneChangeHi() {
		return eventPerKmAggressiveLaneChangeHi;
	}

	public float getEventPerKmBarrierAvoidanceMid() {
		return eventPerKmBarrierAvoidanceMid;
	}

	public float getEventPerKmBarrierAvoidanceHi() {
		return eventPerKmBarrierAvoidanceHi;
	}

	public float getEventPerKmSpinningHi() {
		return eventPerKmSpinningHi;
	}

	public float getEventPerKmSpeedLimit() {
		return eventPerKmSpeedLimit;
	}
	
	// geteri za prekoracenja u sumama
	public float getBoundaryOfEvents() {
		return boundaryOfEvents;
	}

	// traveled distance po velicini u km [ zero, testing, mid, average, hi ]
	public int getTotalDistanceZeroKm() {
		return totalDistanceZeroKm;
	}

	public float getTestingTraveledDistance() {
		return testingTraveledDistance;
	}

	public int getTotalDistanceMidKm() {
		return totalDistanceMidKm;
	}
	
	public int getTotalDistanceAverageKm() {
		return totalDistanceAverageKm;
	}
	
	public int getTotalDistanceHiKm() {
		return totalDistanceHiKm;
	}

	public int getMinBonusPoints() {
		return minBonusPoints;
	}
    
	// pomocne funkcije	
	public float getPointsForSeverity1(float numEvent, float eventPerKm) {
		float points = 0;
		
		float n = numEvent/eventPerKm;
		
		if( this.getBoundaryOfEvents() >= n) {
			points = numEvent;
		} else {
			points = numEvent + (float) Math.exp(n);
		}
		
		return points;
	}
	
	public float getPointsForSeverity2(float numEvent, float eventPerKm) {
		float points = 0;
		
		float n = numEvent/eventPerKm;
		
		if( this.getBoundaryOfEvents() >= n) {
			points = numEvent;
		} else {
			points = numEvent + (float) Math.exp(n);
		}
		
		return points;
	}

	public String getTypeOfDriver() {
		return typeOfDriver;
	}

	public void setTypeOfDriver(String typeOfDriver) {
		this.typeOfDriver = typeOfDriver;
	}	
}
