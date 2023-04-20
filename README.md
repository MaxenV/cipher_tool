# cipher_tool

Main use is repair damaged bytes in ciphered files.
The program allows you to read binary file and decrypt them.

## When it works

Cipher class require bash and installed openssl.
The file must be encrypted in cbc mode to be repairable.

## Usage

Program was tested on "aes-256" cipher;

### BinaryFile object

BinaryFile(filePath) - object used for binary operation on files
get_text() - return content of the file as text
getBinaryArray() - return binary content of the file as int array
getBinFile() - return file object
useCommand(command) - use command in bash
write_file() - briet in file

### CipherTool object

CipherTool(bnnaryArray, cipherName, mode, key, iv) - object for working with ciphers

binnaryArray - int array with binary content from file
cipherName - cipers names you can find on [opensll documentation](https://www.openssl.org/docs/man1.1.1/man1/enc.html) e.g. "aes-256"
mode - mode in which the file was encrypted (repairing works only in "cbc" mode)
key - key used to encript and decript file
iv - an initialization vector

allowedChars - characters that may be in the file (default a-z,A-Z,0-9,\n)

#### Methods

repair() - repair file if it's broken
find_wrong(arr) - return id of block with chars not contain in allowedChars (or -1 if arr is ok)
isBroken(block) - check if block is broken
