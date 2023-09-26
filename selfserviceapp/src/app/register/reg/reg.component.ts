import { Component } from '@angular/core';
import {Validators, FormBuilder, FormGroup, FormControl} from '@angular/forms'
import {Route, Router} from '@angular/router'
import { ActivatedRoute } from '@angular/router';
import Validateform from 'src/app/helpers/validateform';

@Component({
  selector: 'app-reg',
  templateUrl: './reg.component.html',
  styleUrls: ['./reg.component.css']
})
export class RegComponent {
  regForm!: FormGroup
  constructor(private fb: FormBuilder, private router:Router) //inject formbuilder
  {

  }
  ngOnInit():void{
  this.regForm = this.fb.group({
    name:['',Validators.required],
    pwd:['',Validators.required],
    mobile:['',Validators.required],
    email:['',Validators.required],
  })}

  onsubmit()
  {
    if(this.regForm.valid)
    {
      console.log(this.regForm.value)
      //send val to db
      this.router.navigate(['/home'])
    }
    else{
      //throw error using toaster
      Validateform.validateform(this.regForm);
    }
  }

  
}
