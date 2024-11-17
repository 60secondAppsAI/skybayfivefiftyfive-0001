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
            <rewardProgram-table
            v-if="rewardPrograms && rewardPrograms.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:rewardPrograms="rewardPrograms"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-reward-programs="getAllRewardPrograms"
             >

            </rewardProgram-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import RewardProgramTable from "@/components/RewardProgramTable";
import RewardProgramService from "../services/RewardProgramService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    RewardProgramTable,
  },
  data() {
    return {
      rewardPrograms: [],
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
    async getAllRewardPrograms(sortBy='rewardProgramId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await RewardProgramService.getAllRewardPrograms(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.rewardPrograms.length) {
					this.rewardPrograms = response.data.rewardPrograms;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching rewardPrograms:", error);
        }
        
      } catch (error) {
        console.error("Error fetching rewardProgram details:", error);
      }
    },
  },
  mounted() {
    this.getAllRewardPrograms();
  },
  created() {
    this.$root.$on('searchQueryForRewardProgramsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllRewardPrograms();
    })
  }
};
</script>
<style></style>
