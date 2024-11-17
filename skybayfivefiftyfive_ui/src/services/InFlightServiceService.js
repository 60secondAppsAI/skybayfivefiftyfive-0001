import http from "../http-common"; 

class InFlightServiceService {
  getAllInFlightServices(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/inFlightService/inFlightServices`, searchDTO);
  }

  get(inFlightServiceId) {
    return this.getRequest(`/inFlightService/${inFlightServiceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/inFlightService?field=${matchData}`, null);
  }

  addInFlightService(data) {
    return http.post("/inFlightService/addInFlightService", data);
  }

  update(data) {
  	return http.post("/inFlightService/updateInFlightService", data);
  }
  
  uploadImage(data,inFlightServiceId) {
  	return http.postForm("/inFlightService/uploadImage/"+inFlightServiceId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new InFlightServiceService();
