[![Travis](https://img.shields.io/travis/rust-lang/rust.svg?style=plastic)](https://github.com/systers/powerup-android)

# PowerUp - Android

PowerUp is a female empowerment educational mobile game app that will allow young girls to learn about reproductive health 
and self-esteem by navigating the life of their avatar!
## Some Screenshot
<img src="https://user-images.githubusercontent.com/40147174/76064481-c3603880-5faf-11ea-8421-ed5360e4b57b.png" width="430"> <img src="https://user-images.githubusercontent.com/40147174/76063333-885d0580-5fad-11ea-9e39-ebf5981dde38.png" width="430"> <img src="https://user-images.githubusercontent.com/40147174/76063339-8a26c900-5fad-11ea-91d9-cec0d388772f.png" width="430"> <img src="https://user-images.githubusercontent.com/40147174/76063341-8b57f600-5fad-11ea-82d5-06ce8f7d6716.png" width="430"> <img src="https://user-images.githubusercontent.com/40147174/76063344-8b57f600-5fad-11ea-9676-78efee6c7e28.png" width="430"> <img src="https://user-images.githubusercontent.com/40147174/76063345-8bf08c80-5fad-11ea-8447-ef5d30bb4551.png" width="430"> <img src="https://user-images.githubusercontent.com/40147174/76063348-8d21b980-5fad-11ea-9065-b568b1cc7e6f.png" width="430"> <img src="https://user-images.githubusercontent.com/40147174/76063350-8e52e680-5fad-11ea-977f-c16b41d6d8a6.png" width="430">


## Setup for Developers
1. Make sure you have downloaded the latest version of [Android Studio](https://developer.android.com/sdk/index.html). It works on Linux, Windows and Mac. Download the correct version for your OS.
2. Go to [the project repo](https://github.com/systers/powerup-android/) and fork it by clicking "Fork" 
3. If you are working on Windows, download [Git Bash for Windows](https://git-for-windows.github.io/) to get a full Unix bash with Git functionality
4. Clone the repo to your desktop `git clone https://github.com/YOUR_USERNAME/powerup-android.git`
5. Open the project with Android Studio 
6. Fetch the latest version of code from branch "[develop](https://github.com/systers/powerup-android/tree/develop/PowerUp)".
7. Build a 'Powerup' application which is inside the base directory.

## Configure remotes
When a repository is cloned, it has a default remote called `origin` that points to your fork on GitHub, not the original repository it was forked from. To keep track of the original repository, you should add another remote named `upstream`:

1. Set the `upstream`:

   `git remote add upstream https://github.com/systers/powerup-android.git`
  
1. Run `git remote -v` to check the status, you should see something like the following:

  > origin    https://github.com/YOUR_USERNAME/powerup-android.git (fetch)
  
  > origin    https://github.com/YOUR_USERNAME/powerup-android.git (push)
  
  > upstream  https://github.com/systers/powerup-android.git (fetch)
  
  > upstream  https://github.com/systers/powerup-android.git (push)

1. To update your local copy with remote changes, run the following:

   `git fetch upstream`

   `git rebase upstream/develop`

   This will give you an exact copy of the current remote, make sure you don't have any local changes.

## Contributing and developing a feature
1. Make sure you are in the develop branch `git checkout develop`
1. Create a new branch with a meaningful name `git checkout -b branch_name`
1. Develop your feature on Android Studio and run it using the emulator or connecting your own Android device
1. Clean your project from Android Studio `Build/Clean project`
1. Add the files you changed `git add file_name` (avoid using `git add .`)
1. Commit your changes `git commit -m "Message briefly explaining the feature"`
1. Keep one commit per feature. If you forgot to add changes, you can edit the previous commit `git commit --amend`
1. Push to your repo `git push origin branch-name`
1. Go into [the Github repo](https://github.com/systers/powerup-android/) and create a pull request explaining your changes
1. If you are requested to make changes, edit your commit using `git commit --amend`, push again and the pull request will edit automatically
1. You will need to add a message on the pull request notifying your changes to your reviewer

## Contributing Guidelines 
[Click](https://github.com/systers/powerup-android/wiki/How-to-Contribute) here to find the contributing guidelines for the project and follow them before sending a contribution.

## Documentation of PowerUp (Android)
Here's the link to the official documentation:
[Visit Documentation!](https://github.com/systers/powerup-android/wiki#overview)

## Coding Guidelines
1. Don't use magic numbers or hard-coded strings. Put them in dimens.xml or strings.xml
1. Class names should be in CamelCase. Name activities with names including Activity so it's easier to know what they are.
1. Include spaces between parameters when you call a method for example: `Intent(MainActivity.this, GameActivity.class)`.
1. Give relevant names to buttons and other resources. 
1. Use `@id` instead of `@+id` when referring to resources that have been already created in xml files.

## Contact
You can reach the maintainers and our community on [AnitaB.org Open Source Zulip](https://anitab-org.zulipchat.com/#). If you are interested in contributing to the powerUP, we have a dedicated stream for this project [#powerUp](https://anitab-org.zulipchat.com/#narrow/stream/222537-powerup), where you can ask questions and interact with the community, join with us!
