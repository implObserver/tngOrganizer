@Composable
fun ProgramListWidget(programs: List<ProgramEntity>) {
    LazyColumn {
        items(programs) { program ->
            ProgramItem(program)
        }
    }
}