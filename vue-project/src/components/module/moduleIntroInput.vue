<template>
  <b-card class="border-secondary shadow" bg-variant="light">
    <template #header>
      <h3>Edit Introduction of {{ moduleName.toUpperCase() }} Module</h3>
    </template>
    <b-card-text>
      <h5>Old Intro:</h5>
      {{ moduleIntro }}
    </b-card-text>
    <b-form @submit="sendIntro" @reset="resetIContent">
      <b-form-group id="test-area-1">
        <b-form-textarea placeholder="Write the Introduction..."
                         rows="5"
                         v-model="introContent"
                         required>
        </b-form-textarea>
      </b-form-group>
      <div class="mt-2 mb-0 float-right">
        <b-button class="shadow-sm" variant="warning" type="reset">Reset</b-button>
        <b-button class="shadow-sm" variant="primary" type="submit">Post</b-button>
      </div>
    </b-form>
  </b-card>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "moduleIntroInput",
  props: ['moduleName'],
  data() {
    return {
      moduleIntro: '',
      introContent: ''
    }
  },
  computed: {
    ...mapGetters ({
      modulesList: 'moduleList/getModuleList',
    })
  },
  methods: {
    resetIContent() {
      this.introContent = ''
    },
    sendIntro() {
      // Alert component construction
      const crtEl = this.$createElement
      const errTitle = crtEl(
        'p',
        { class: ['text-center', 'mb-0'] },
        [
          crtEl('b-icon', { props:{ icon: 'exclamation-diamond', small: true } }),
          crtEl('strong', ' Error')
        ]
      )

      // Show alert when the like action is done by a visitor
      if(!this.introContent)
        this.$bvToast.toast(
          'Cannot save empty introduction.',{
            title: [errTitle],
            toaster: 'b-toaster-top-center',
            variant: 'danger',
            solid: true
          }
        )
      // Communication
      else{
        axios
          .post('/editintro', null, {params: {
              module_name: this.moduleName,
              new_intro: this.introContent
            }
          })
          .then(response=>{
            console.log(response)
            if(response.data.code === 200){
              this.resetIContent()
            }
          })
          .catch(failResponse=>{
            console.log(failResponse)
          })
      }
    }
  },
  mounted() {
    this.$store.dispatch('moduleList/getModulefromServer')
    this.$nextTick()

    // this.moduleIntro = this.modulesList[0].module_intro
    let module
    for(let i=0; i<this.modulesList.length; i++){
      module = this.modulesList[i]
      if(module.module_name === this.moduleName){
        this.moduleIntro = module.module_intro
        this.introContent = this.moduleIntro
        break
      }
    }

  }
}
</script>

<style scoped>

</style>
