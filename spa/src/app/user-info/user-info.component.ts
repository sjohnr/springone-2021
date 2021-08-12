import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  user$: Observable<any>;

  constructor(
    private userService: UserService,
  ) { }

  ngOnInit(): void {
    this.user$ = this.userService.getUserInfo();
  }

}
