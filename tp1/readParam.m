function param = readParam(paramName)
    pathName = 'configFile.txt';
    separator = '=';
    allParams =importdata(pathName, separator);
    for i = 1:size(allParams.textdata)
       if (strcmp(allParams.textdata(i), paramName))
           param = allParams.data(i);
       end
    end
end
