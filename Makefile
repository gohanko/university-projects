CC = g++

SRC_DIR := ./src/
OBJ_DIR := ./build/
SRC_FILES := $(wildcard $(SRC_DIR)/**/*.cpp)
OBJ_FILES := $(patsubst $(SRC_DIR)/%/%.cpp,$(OBJ_DIR)/%/%.o,$(SRC_FILES))
LDFLAGS := -L ./library/SFML-2.5.1/lib -lsfml-graphics -lsfml-window -lsfml-system
CPPFLAGS := -I ./library/SFML-2.5.1/include

clean_object:
	del /s /q .\build\*.o

clean_all:
	del /s /q .\build\*

copy_assets:
	xcopy /E .\assets .\build\assets
	
copy_dependencies:
	copy .\library\SFML-2.5.1\bin\sfml-graphics-2.dll .\build\sfml-graphics-2.dll
	copy .\library\SFML-2.5.1\bin\sfml-system-2.dll .\build\sfml-system-2.dll
	copy .\library\SFML-2.5.1\bin\sfml-window-2.dll .\build\sfml-window-2.dll

$(OBJ_DIR)/%.o: $(SRC_DIR)/%.cpp
	$(CC) $(CPPFLAGS) -c -o $@ $<
	@echo Executed

build_app: $(OBJ_FILES)
	$(CC) $(LDFLAGS) -o ./build/main.exe $^

fresh_build: clean_all copy_assets copy_dependencies build_app clean_object
	build/main.exe
