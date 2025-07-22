import {ChangeDetectionStrategy, Component, OnInit, signal} from '@angular/core';
import { RouterOutlet } from '@angular/router';

import {Memo} from './memo';
import {MemoService} from './memo.service';
import {HttpErrorResponse} from '@angular/common/http';
import {CommonModule} from '@angular/common';


@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.html',
  imports: [CommonModule],
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title = signal('memento');
  public memos: Memo[] = [];
  constructor(private memoService: MemoService) {}

  ngOnInit() {
    this.getMemos();
  }

  public getMemos(): void {
    this.memoService.getMemos().subscribe({
      next: (response: Memo[]) => {
        this.memos = response;
        console.log(this.memos);
        console.log("WORKS MEMOS");
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
        console.log("NO MEMOS");
      }
    });
  }
}
