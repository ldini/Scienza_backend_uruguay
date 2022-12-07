package ar.com.scienza.remote.json.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GoogleMapsResultResponseRDto {

	@SerializedName("address_components")
	private List<GoogleMapsAddressComponentResponseRDto> addressComponents = new ArrayList<GoogleMapsAddressComponentResponseRDto>();
	
	@SerializedName("geometry")
	private GoogleMapsGeometryResponseRDto geometry;

	
	public List<GoogleMapsAddressComponentResponseRDto> getAddressComponents() {
		return addressComponents;
	}

	public void setAddressComponents(
			List<GoogleMapsAddressComponentResponseRDto> addressComponents) {
		this.addressComponents = addressComponents;
	}

	public GoogleMapsGeometryResponseRDto getGeometry() {
		return geometry;
	}

	public void setGeometry(GoogleMapsGeometryResponseRDto geometry) {
		this.geometry = geometry;
	}
	
	
	/**
	 * Busca entre los componentes alguno que coincida con el type
	 * @param type
	 * @return
	 */
	public GoogleMapsAddressComponentResponseRDto findAddressComponentByType(String type) {
		
		for(GoogleMapsAddressComponentResponseRDto addressComponent : this.addressComponents)
			if(addressComponent.getTypes().contains(type))
				return addressComponent;
		
		return null;
	}
}