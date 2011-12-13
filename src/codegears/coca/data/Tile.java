package codegears.coca.data;

import org.w3c.dom.Node;

public class Tile {

	public static int BUILDING_NOTOCCUPY = 0;
	
	private String landType;
	private Boolean isOccupy;
	private String buildingId;
	private int progress;
	private int supply;
	private String extraId;
	private int rottenPeriod;
	
	private Building building;
	
	public void setDataFromXmlNode(Node node){
		landType = node.getAttributes().getNamedItem("land_type").getNodeValue();
		isOccupy = Boolean.valueOf(node.getAttributes().getNamedItem("is_occupy").getNodeValue());
		buildingId = node.getAttributes().getNamedItem("building_id").getNodeValue();
		progress = Integer.parseInt(node.getAttributes().getNamedItem("progress").getNodeValue());
		supply = Integer.parseInt(node.getAttributes().getNamedItem("supply_left").getNodeValue());
		extraId = node.getAttributes().getNamedItem("extra_id").getNodeValue();
		rottenPeriod = Integer.parseInt(node.getAttributes().getNamedItem("rotten_period").getNodeValue());
	}
	
	public int getBuildingStatus(){
		return 0;
	}
	
	public String getBuildingId(){
		return this.buildingId;
	}
	
	public String getLandType(){
		return this.landType;
	}
	
	public void setBuilding(Building setValue){
		this.building = setValue;
	}
	
}