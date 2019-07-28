package by.letum8658.homework.dz12

private val BASE_URL =
    "https://api.backendless.com/F00E0C05-CBCE-C794-FF0C-7B52AC3A9D00/6E3DCCB0-A191-2E81-FF25-F71A07CFE400/"

fun provideStudentRepository(): StudentRepository {

    return StudentRepositoryRemote(
        NetProvider.provideStudentApi(
            NetProvider.provideRetrofit(
                BASE_URL,
                NetProvider.provideOkHttp(),
                NetProvider.provideGson()
            )
        )
    )
}