<template>
  <div>
    <b-row align-h="center" class="mb-4" v-if="current_user">
      <b-button variant="danger"
                style="min-width: 60rem"
                @click="$bvModal.show('create-module-request')">
        <h5>
          Don't find modules interested in? [ Apply For A New Module Here ]
        </h5>
      </b-button>

      <b-modal id="create-module-request" title="Apply For A New Module" centered>
        Module Name:
        <b-input v-model="module_name"></b-input>
        <template #modal-footer>
          <b-button variant="dark" @click="hideApplyModal">Cancel</b-button>
          <b-button variant="warning" @click="applyForModule">Submit</b-button>
        </template>
      </b-modal>
    </b-row>

    <b-row align-h="center">
      <b-col cols="auto">
        <b-jumbotron
          v-for="singleModule in moduleList[0]"
          :key="singleModule.module_name"
          :header="singleModule.module_name.toUpperCase()"
          :lead="singleModule.module_intro"
          :id="singleModule.module_name+'_block'"
          border-variant="secondary"
          style="max-width: 40rem"
          class="mx-auto"
          bg-variant="dark"
          text-variant="white">
          <b-button
            variant="warning"
            :id="singleModule.module_name+'_button'"
            :to="'knowledge-modules/'+singleModule.module_name">
            ENTER
          </b-button>
        </b-jumbotron>
      </b-col>
      <b-col cols="auto">
        <b-jumbotron
          v-for="singleModule in moduleList[1]"
          :key="singleModule.module_name"
          :header="singleModule.module_name.toUpperCase()"
          :lead="singleModule.module_intro"
          :id="singleModule.module_name+'_block'"
          border-variant="secondary"
          style="max-width: 40rem"
          class="mx-auto"
          bg-variant="dark"
          text-variant="white">
          <b-button
            variant="warning"
            :id="singleModule.module_name+'_button'"
            :to="'knowledge-modules/'+singleModule.module_name">
            ENTER
          </b-button>
        </b-jumbotron>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: "moduleCard",
  // props: ['multiModules'],
  data() {
    return{
      multiModules: [],
      moduleList: [],
      module_name: ''
    }
  },
  computed:{
    ...mapGetters({
      current_user: "loginInfo/getLUName"
    })
  },
  methods:{
    preprocessModuleList() {
      const list_size = this.multiModules.length
      let tmp_list = []

      for(let i = 0; i<list_size/2; i++){
        tmp_list.push(this.multiModules[i])
      }
      this.moduleList.push(tmp_list)
      tmp_list = []

      for(let i = list_size-1; i>=list_size/2; i--){
        tmp_list.push(this.multiModules[i])
      }
      this.moduleList.push(tmp_list)
    },
    applyForModule() {
      this.$axios
        .post('/createmodulerequest', null, {params:{module_name: this.module_name}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.constructSuccessToast(
              'Successfully send the request, please wait for the admin to review')
          }
          else{
            this.constructFailureToast('Module with the same name has existed')
          }
        })
      .catch(failResponse=>{
        console.log(failResponse)
      })
      this.hideApplyModal()
    },
    hideApplyModal(){
      this.$bvModal.hide('create-module-request')
    },
    constructSuccessToast(action) {
      // Alert component construction
      const crtEl = this.$createElement
      const errTitle = crtEl(
        'p',
        { class: ['text-center', 'mb-0'] },
        [
          crtEl('b-icon', { props:{ icon: 'exclamation-diamond', small: true } }),
          crtEl('strong', ' Success')
        ]
      )
      // Show alert
      this.$bvToast.toast(
        action+'!',{
          title: [errTitle],
          toaster: 'b-toaster-top-center',
          variant: 'success',
          solid: true
        }
      )
    },
    constructFailureToast(action) {
      // Alert component construction
      const crtEl = this.$createElement
      const errTitle = crtEl(
        'p',
        { class: ['text-center', 'mb-0'] },
        [
          crtEl('b-icon', { props:{ icon: 'exclamation-diamond', small: true } }),
          crtEl('strong', ' Fail')
        ]
      )
      // Show alert
      this.$bvToast.toast(
        action+'!',{
          title: [errTitle],
          toaster: 'b-toaster-top-center',
          variant: 'danger',
          solid: true
        }
      )
    },
    getModulefromServer() {
      axios
        .get('/getmodule', {
          params:{
            choice: 2
          }
        })
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.multiModules = response.data.data
            this.preprocessModuleList()
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  beforeMount() {
    this.getModulefromServer()
  }
}
</script>

<style scoped>

</style>
