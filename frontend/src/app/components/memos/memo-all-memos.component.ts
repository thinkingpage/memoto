import {UserProfileService} from '../../keycloak/user-profile.service';
import {Component} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MemoStateService} from './memo-state.service';

@Component({
  selector: 'app-all-memos',
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: 'memo-all-memos.component.html',
})

export class MemoAllMemosComponent {

  constructor (
    public memoStateService: MemoStateService,
    protected userProfileService: UserProfileService
  )
  {}

  // goal: save custom color variants in database.
  // colorVariants should have a font color too

  private colorVariants = [
    'bg-[#AF964C]',
    'bg-[#BB917B]',
    'bg-[#cab988]'
  ]


  backgroundColorGenerator(id: number | undefined) {
    if(id) {
      return this.colorVariants[(id % this.colorVariants.length)];
    }
    return;
  }

  showAllMemosFromUser(username: string): void {
    if(username) {
      this.memoStateService.usernameFromClicked.set(username);
    }
  }

  deleteMemoById(id: number): void {
    console.log(id);
    if (id) {
      this.memoStateService.memoToDelete.set(id);
    }
  }
}
