import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RailwayService } from 'src/app/_service/railway.service';

@Component({
  selector: 'app-railway-add',
  templateUrl: './railway-add.component.html',
  styleUrls: ['./railway-add.component.css']
})
export class RailwayAddComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private router: Router, private railwayService: RailwayService) { }

  addForm!: FormGroup;

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      railwayNumber: ['', Validators.required],
      departureStation: ['', Validators.required],
      arrivalStation: ['', Validators.required],
      departureDatetime: ['', Validators.required],
      arrivalDatetime: ['', Validators.required]
    });
  }

  onSubmit() {
    this.railwayService.create(this.addForm.value)
      .subscribe(data => {
        console.log(data);
        this.router.navigate(['home']);
      });
  }

}