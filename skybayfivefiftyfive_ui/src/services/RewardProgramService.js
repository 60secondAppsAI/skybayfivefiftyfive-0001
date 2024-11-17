import http from "../http-common"; 

class RewardProgramService {
  getAllRewardPrograms(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/rewardProgram/rewardPrograms`, searchDTO);
  }

  get(rewardProgramId) {
    return this.getRequest(`/rewardProgram/${rewardProgramId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/rewardProgram?field=${matchData}`, null);
  }

  addRewardProgram(data) {
    return http.post("/rewardProgram/addRewardProgram", data);
  }

  update(data) {
  	return http.post("/rewardProgram/updateRewardProgram", data);
  }
  
  uploadImage(data,rewardProgramId) {
  	return http.postForm("/rewardProgram/uploadImage/"+rewardProgramId, data);
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

export default new RewardProgramService();
