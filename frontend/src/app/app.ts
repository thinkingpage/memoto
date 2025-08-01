import {Component, OnInit, signal} from '@angular/core';

import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {FilterComponent} from './components/filters/filter.component';
import {MemoService} from './memo.service';
import {MemoModel} from './models/memo.model';
import {HttpErrorResponse} from '@angular/common/http';
import {MemoItemComponent} from './components/memos/memo-item.component';
import {MemoAddMemo} from './components/memos/memo-add-memo';
import {MemoAllMemosComponent} from './components/memos/memo-all-memos.component';
import {map} from 'rxjs';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    HeaderComponent,
    FilterComponent,
    MemoAllMemosComponent,
    FooterComponent,
    MemoAddMemo,
    MemoItemComponent
  ],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App implements OnInit {
  protected readonly title = signal('memoto');

  showAddMemoComponent = signal<boolean>(false);
  showMemoById = signal<boolean>(false);
  showAllMemoComponent = signal<boolean>(false);
  memos = signal<MemoModel[]>([]);
  memo = signal<MemoModel | null>(null);

  constructor(private memoService: MemoService) {}

  // TODO (future) lesser network bandwidth -> update changes locally.
  // (https://stackoverflow.com/questions/69800897/angular-10-reload-after-delete)


  // 1. onInit load all Memos.
  // 2. if needed (copy those memos?) and change the array so that the ui will update without manual reload
  //    for this I need to

  ngOnInit() {
    this.loadMemos();
  }

  loadMemos() {
    // TODO: broski: maybe this part shouldn't be subscribed to, since it actually "can't wait"? it's needed (or at least a part of it)
    this.memoService.getMemos().subscribe({
      next: (response: MemoModel[]) => {
        this.memos.set(response);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    });
  }

  activateAddForm() {
    this.showAddMemoComponent.update(value => !value);
  }

  activateMemoList() {
    this.showAllMemoComponent.update(value => !value);
    if(!this.showAllMemoComponent()) {
      return;
    }
  }

  addMemo(memo: MemoModel) {

    this.memoService.addMemo(memo).subscribe({
        next: (response: MemoModel) => {
          this.memos.update(value => {
              return [...value, response];
            }
          )
        },
        error: (error: HttpErrorResponse) => {
          console.log(error.message);
        },
      }
    )
  }

  // TODO: fix this.
  // https://www.youtube.com/watch?v=wcn_8UnYBEw
  // PROBLEM gefunden! splice() -> ARRAY-INDEX angeben, die id nützt in dem fall nix. man muss die memo.id vergleichen der vlaue!
  memoDeleteById(id: number): void {

    this.memoService.deleteMemo(id).subscribe({
      next: () => {
        this.memos.update(value => {
            return value.filter(memo => memo.id !== id);
          }
        )
      },
      error: (error: HttpErrorResponse) => console.log(error.message),
    });
  }

  showMemoByIdComponent() {
    // this.showMemoById.update(value => !value);
  }

  memoById(id: number): void {
    this.memoService.getMemo(id).subscribe({
      next: (memo: MemoModel) => {
        this.memo.set(memo);
      },
      error: (error: HttpErrorResponse) => console.log(error.message)
    })
  }
}
