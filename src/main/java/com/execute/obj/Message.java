package com.execute.obj;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.execute.obj.event.SpeedLimit;
import com.execute.obj.event.SpinningHi;


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
	protected float eventPerKmSpinningHiInWinter;

	//ako je moguc SpinningHi
	protected boolean possibleSpinningHi;
	protected String dateFrom;
	protected String dateTo;
		
	
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
	protected int boundaryTraveledDistanceKm;
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
    protected String driverId;
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
    protected int numHiSpinningAllowed;
    protected int numHiSpinningNotAllowed;
    protected int numSpeedLimit;
    protected ArrayList<SpinningHi> spinningList = new ArrayList<SpinningHi>();
    protected ArrayList<SpeedLimit> speedLimitList = new ArrayList<SpeedLimit>();
    
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
    protected float partSpinningHiAllowed;
    protected float partSpinningHiNotAllowed;
    
    // rezultati
    protected String report;
    /**
     * none - no set type of driver
     * unknown - we don't have enough data to determine driver behaviour
     * test-driver - driver was probably just testing a vehicle and installed device
     * exelent - drivers that total sum is 0
     * ok - safe group
     * border - drivers at the border risk
     * risk - risk group of drivers
     * agresive - agresive driver
     * extreme - too agresive driver
     */
    protected float eventOfLowerImportance;
    protected float eventOfHighImportance;
    protected String typeOfDriver;
    protected String processedAbruptTurningHi = "No";
    protected String processedAccHi = "No";
    protected String processedAccMid = "No";
    protected String processedAggressiveLaneChangeHi = "No";
    protected String processedAggressiveLaneChangeMid = "No";
    protected String processedBarrierAvoidanceHi = "No";
    protected String processedBarrierAvoidanceMid = "No";
    protected String processedCorneringHi = "No";
    protected String processedCorneringMid = "No";
    protected String processedDecHi = "No";
    protected String processedDecMid = "No";
    protected String processedSkiddingHi = "No";
    protected String processedSpeedLimit = "No";
    protected String processedSpinningHiAllowed = "No";
    protected String processedSpinningHiNotAllowed = "No";
    protected String additionallyConclusion1 = "No";
    protected String additionallyConclusion2 = "No";
    protected String additionallyConclusion3 = "No";
	
            
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
    	this.eventPerKmSpeedLimit = 46; //46
    	this.eventPerKmSpinningHi = 0;
    	this.eventPerKmSpinningHiInWinter = 2;
    	
    	this.possibleSpinningHi = false;
    	
    	this.testingTraveledDistance = 10;
    	this.kmBase = 1000;
    	
    	this.dateFrom = "2014-05-20";
    	this.dateTo = "2014-05-30";

    	this.totalDistanceZeroKm = 0;
    	this.boundaryTraveledDistanceKm = 100; 
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
    	this.partSpinningHiAllowed = Integer.MIN_VALUE;
    	this.partSpinningHiNotAllowed = Integer.MIN_VALUE;
    	
    	this.boundaryOfEvents = (float) 1.5;   
    	this.typeOfDriver = "none";
    	this.eventOfLowerImportance = Integer.MIN_VALUE;
    	this.eventOfHighImportance =  Integer.MIN_VALUE;
    }   
    
//    public void setRestData() {
	public void setRestData(JSONObject obj) {
    	this.numHiAbruptTurning = ((Long) obj.get("numHiAbruptTurning")).intValue();
    	this.numHiAcc = ((Long) obj.get("numHiAcc")).intValue();
    	this.numHiAggressiveLaneChange = ((Long) obj.get("numHiAggressiveLaneChange")).intValue();
    	this.numHiBarrierAvoidance = ((Long) obj.get("numHiBarrierAvoidance")).intValue();
    	this.numHiCornering = ((Long) obj.get("numHiCornering")).intValue();
    	this.numHiDec = ((Long) obj.get("numHiDec")).intValue();    	
    	this.numHiSkidding = ((Long) obj.get("numHiSkidding")).intValue();
    	this.numMidAcc = ((Long) obj.get("numMidAcc")).intValue();
    	this.numMidAggressiveLaneChange = ((Long) obj.get("numMidAggressiveLaneChange")).intValue();
    	this.numMidBarrierAvoidance = ((Long) obj.get("numMidBarrierAvoidance")).intValue();
    	this.numMidCornering = ((Long) obj.get("numMidCornering")).intValue();
    	this.numMidDec = ((Long) obj.get("numMidDec")).intValue();
    	this.numSpeedLimit = ((Long) obj.get("numSpeedLimit")).intValue();
    	this.totalTraveledDistance = ((Long) obj.get("totalTraveledDistance")).floatValue();
    	this.totalEvents = ((Long) obj.get("totalEvents")).intValue();
    	this.driverId = (String) obj.get("vin");
//    	Object vin = obj.get("vin");
    	
    	this.numHiSpinning = ((Long) obj.get("numHiBarrierAvoidance")).intValue();
    	this.numHiSpinningAllowed = Integer.MIN_VALUE;
    	this.numHiSpinningNotAllowed = Integer.MIN_VALUE;
    	    	
    	JSONArray collection1 = (JSONArray) obj.get("speedLimitEvents");
		Iterator<JSONObject> iterator1 = collection1.iterator();
    	
		while (iterator1.hasNext()) {
			JSONObject jo = iterator1.next();
	    	SpeedLimit sl = new SpeedLimit((String)jo.get("messageGroup"), (String)jo.get("eventType"), ((Long) jo.get("speedLimit")).floatValue(), ((Long) jo.get("speedOver")).floatValue());
	    	this.speedLimitList.add(sl);
		}
		
		JSONArray collection2 = (JSONArray) obj.get("spinningEvents");
		Iterator<JSONObject> iterator2 = collection2.iterator();
    	
		while (iterator2.hasNext()) {
			JSONObject jo = iterator2.next();
			SpinningHi sh = new SpinningHi((String)jo.get("messageGroup"), (String)jo.get("eventType"), (String)jo.get("datetimeTriggered"), (String)jo.get("datetimeTriggered"), (String)jo.get("datetimeTriggered"));
	    	this.spinningList.add(sh);
		}
    	
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
	
	public int getNumHiSpinningAllowed() {
		return numHiSpinningAllowed;
	}	
	
	public void setNumSpeedLimit(int numSpeedLimit) {
		this.numSpeedLimit = numSpeedLimit;
	}
	
	public void setNumHiSpinningAllowed(int numHiSpinningAllowed) {
		this.numHiSpinningAllowed = numHiSpinningAllowed;
	}

	public void setNumHiSpinningNotAllowed(int numHiSpinningNotAllowed) {
		this.numHiSpinningNotAllowed = numHiSpinningNotAllowed;
	}

	public int getNumHiSpinningNotAllowed() {
		return numHiSpinningNotAllowed;
	}

	public int getNumSpeedLimit() {
		return numSpeedLimit;
	}
	
	public ArrayList<SpinningHi> getSpinningList() {
		return spinningList;
	}
	
	public ArrayList<SpeedLimit> getSpeedLimitList() {
		return speedLimitList;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public boolean isPossibleSpinningHi() {
		return possibleSpinningHi;
	}

	public void setPossibleSpinningHi(boolean possibleSpinningHi) {
		this.possibleSpinningHi = possibleSpinningHi;
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

	
	public int getTotal() {
		return (int) total;
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

	public float getPartSpinningHiAllowed() {
		return partSpinningHiAllowed;
	}

	public float getPartSpinningHiNotAllowed() {
		return partSpinningHiNotAllowed;
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

	public void setPartSpinningHiAllowed(float partSpinningHi) {
		this.partSpinningHiAllowed = partSpinningHi;
	}
	
	public void setPartSpinningHiNotAllowed(float partSpinningHi) {
		this.partSpinningHiNotAllowed = partSpinningHi;
	}

	public String getDriverId() {
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
	
	public float getEventPerKmSpinningHiInWinter() {
		return eventPerKmSpinningHiInWinter;
	}

	public String getProcessedAbruptTurningHi() {
		return processedAbruptTurningHi;
	}

	public String getProcessedAccHi() {
		return processedAccHi;
	}

	public String getProcessedAccMid() {
		return processedAccMid;
	}

	public String getProcessedAggressiveLaneChangeHi() {
		return processedAggressiveLaneChangeHi;
	}

	public String getProcessedAggressiveLaneChangeMid() {
		return processedAggressiveLaneChangeMid;
	}

	public String getProcessedBarrierAvoidanceHi() {
		return processedBarrierAvoidanceHi;
	}

	public String getProcessedBarrierAvoidanceMid() {
		return processedBarrierAvoidanceMid;
	}

	public String getProcessedCorneringHi() {
		return processedCorneringHi;
	}

	public String getProcessedCorneringMid() {
		return processedCorneringMid;
	}

	public String getProcessedDecHi() {
		return processedDecHi;
	}

	public String getProcessedDecMid() {
		return processedDecMid;
	}

	public String getProcessedSkiddingHi() {
		return processedSkiddingHi;
	}

	public String getProcessedSpeedLimit() {
		return processedSpeedLimit;
	}

	public String getProcessedSpinningHiAllowed() {
		return processedSpinningHiAllowed;
	}

	public String getProcessedSpinningHiNotAllowed() {
		return processedSpinningHiNotAllowed;
	}
		
	public void setProcessedAbruptTurningHi(String processedAbruptTurningHi) {
		this.processedAbruptTurningHi = processedAbruptTurningHi;
	}

	public void setProcessedAccHi(String processedAccHi) {
		this.processedAccHi = processedAccHi;
	}

	public void setProcessedAccMid(String processedAccMid) {
		this.processedAccMid = processedAccMid;
	}

	public void setProcessedAggressiveLaneChangeHi(
			String processedAggressiveLaneChangeHi) {
		this.processedAggressiveLaneChangeHi = processedAggressiveLaneChangeHi;
	}

	public void setProcessedAggressiveLaneChangeMid(
			String processedAggressiveLaneChangeMid) {
		this.processedAggressiveLaneChangeMid = processedAggressiveLaneChangeMid;
	}

	public void setProcessedBarrierAvoidanceHi(String processedBarrierAvoidanceHi) {
		this.processedBarrierAvoidanceHi = processedBarrierAvoidanceHi;
	}

	public void setProcessedBarrierAvoidanceMid(String processedBarrierAvoidanceMid) {
		this.processedBarrierAvoidanceMid = processedBarrierAvoidanceMid;
	}

	public void setProcessedCorneringHi(String processedCorneringHi) {
		this.processedCorneringHi = processedCorneringHi;
	}

	public void setProcessedCorneringMid(String processedCorneringMid) {
		this.processedCorneringMid = processedCorneringMid;
	}

	public void setProcessedDecHi(String processedDecHi) {
		this.processedDecHi = processedDecHi;
	}

	public void setProcessedDecMid(String processedDecMid) {
		this.processedDecMid = processedDecMid;
	}

	public void setProcessedSkiddingHi(String processedSkiddingHi) {
		this.processedSkiddingHi = processedSkiddingHi;
	}

	public void setProcessedSpeedLimit(String processedSpeedLimit) {
		this.processedSpeedLimit = processedSpeedLimit;
	}

	public void setProcessedSpinningHiAllowed(String processedSpinningHiAllowed) {
		this.processedSpinningHiAllowed = processedSpinningHiAllowed;
	}

	public void setProcessedSpinningHiNotAllowed(
			String processedSpinningHiNotAllowed) {
		this.processedSpinningHiNotAllowed = processedSpinningHiNotAllowed;
	}
	
	

	public String getAdditionallyConclusion1() {
		return additionallyConclusion1;
	}

	public String getAdditionallyConclusion2() {
		return additionallyConclusion2;
	}

	public String getAdditionallyConclusion3() {
		return additionallyConclusion3;
	}

	public void setAdditionallyConclusion1(String additionallyConclusion1) {
		this.additionallyConclusion1 = additionallyConclusion1;
	}

	public void setAdditionallyConclusion2(String additionallyConclusion2) {
		this.additionallyConclusion2 = additionallyConclusion2;
	}

	public void setAdditionallyConclusion3(String additionallyConclusion3) {
		this.additionallyConclusion3 = additionallyConclusion3;
	}

	public float getEventOfLowerImportance() {
		return eventOfLowerImportance;
	}

	public float getEventOfHighImportance() {
		return eventOfHighImportance;
	}

	public void setEventOfLowerImportance(float eventOfLowerImportance) {
		this.eventOfLowerImportance = eventOfLowerImportance;
	}

	public void setEventOfHighImportance(float eventOfHighImportance) {
		this.eventOfHighImportance = eventOfHighImportance;
	}

	// geteri za prekoracenja u sumama
	public float getBoundaryOfEvents() {
		return boundaryOfEvents;
	}

	// traveled distance po velicini u km [ zero, testing, mid, average, hi ]
	public int getTotalDistanceZeroKm() {
		return totalDistanceZeroKm;
	}
	
	public int getBoundaryTraveledDistanceKm() {
		return boundaryTraveledDistanceKm;
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
