import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms'
// import { MatSnackBar } from '@angular/material';
import {Route, Router} from '@angular/router'
import { ActivatedRoute } from '@angular/router';
import Validateform from 'src/app/helpers/validateform';

@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.css']
})
export class LogComponent {
  loginForm!: FormGroup
  constructor(private fb: FormBuilder, private router:Router) //inject formbuilder
  {

  }
  ngOnInit():void{
  this.loginForm = this.fb.group({
    username:['',Validators.required],
    password:['',Validators.required]
  })}

  onsubmit()
  {
    if(this.loginForm.valid)
    {
      console.log(this.loginForm.value)
      // this.snackBar.open('Login Successful','',{duration:1000})
      this.router.navigate(['/home'])
      //send val to db
    }
    else{
      //throw error using toaster
      Validateform.validateform(this.loginForm);
    }
  }
}
