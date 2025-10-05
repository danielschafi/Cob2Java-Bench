from rewrite_file_paths import replace_all_paths_with_filenames


source_dir = "data/the_Stack/gt100"


# filter out files that accept inputs from the command line (because we are not able to rewrite those with args)

# 1. replace all pathes (relative, absolute paths) with only the filename e.g. ../../../data/lab9.out -> lab9.out
replace_all_paths_with_filenames(source_dir)
