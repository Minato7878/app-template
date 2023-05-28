import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { RailwayService } from 'src/app/_service/railway.service';

@Component({
  selector: 'app-railway-edit',
  templateUrl: './railway-edit.component.html',
  styleUrls: ['./railway-edit.component.css']
})
export class RailwayEditComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private router: Router, private activatedRoute: ActivatedRoute,
    private railwayService: RailwayService) { }

  editForm!: FormGroup;
  railwayId!: number;

  ngOnInit() {
    this.editForm = this.formBuilder.group({
      railwayNumber: ['', Validators.required],
      departureStation: ['', Validators.required],
      arrivalStation: ['', Validators.required],
      departureDatetime: ['', Validators.required],
      arrivalDatetime: ['', Validators.required]
    });
    this.activatedRoute.params.subscribe(params => {
      this.railwayId = params['id'];
      this.railwayService.get(this.railwayId).subscribe(railway => {
        this.editForm.controls['railwayNumber'].setValue(railway.railwayNumber);
        this.editForm.controls['departureStation'].setValue(railway.departureStation);
        this.editForm.controls['arrivalStation'].setValue(railway.arrivalStation);
        this.editForm.controls['departureDatetime'].setValue(this.formatDatetime(railway.departureDatetime));
        this.editForm.controls['arrivalDatetime'].setValue(this.formatDatetime(railway.arrivalDatetime));
      });
    });
  }

  onSubmit() {
    this.railwayService.update(this.editForm.value, this.railwayId)
      .subscribe(data => {
        console.log(data);
        this.router.navigate(['home']);
      });
  }

  private formatDatetime(datetime: Date): string {
    const formattedDate = datetime.toISOString().slice(0, 10); // Extract the date portion from the ISO string
    const formattedTime = datetime.toISOString().slice(11, 16); // Extract the time portion from the ISO string
    return `${formattedDate}T${formattedTime}`;
  }
}