<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <inFlightService-table
            v-if="inFlightServices && inFlightServices.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:inFlightServices="inFlightServices"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-in-flight-services="getAllInFlightServices"
             >

            </inFlightService-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import InFlightServiceTable from "@/components/InFlightServiceTable";
import InFlightServiceService from "../services/InFlightServiceService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    InFlightServiceTable,
  },
  data() {
    return {
      inFlightServices: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllInFlightServices(sortBy='inFlightServiceId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await InFlightServiceService.getAllInFlightServices(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.inFlightServices.length) {
					this.inFlightServices = response.data.inFlightServices;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching inFlightServices:", error);
        }
        
      } catch (error) {
        console.error("Error fetching inFlightService details:", error);
      }
    },
  },
  mounted() {
    this.getAllInFlightServices();
  },
  created() {
    this.$root.$on('searchQueryForInFlightServicesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllInFlightServices();
    })
  }
};
</script>
<style></style>
