# Video-Stream-Processing-with-Apache-Storm

Designing and implement a real-time image processing application using Apache Storm

## table of contents

**[Objective](#objective)**

**[Framework and Tools](#framework-and-tools)**

**[Expected Outcome](#expected-outcome)**

**[Main Concepts in Apache Storm](#main-concepts-in-apache-storm)**

**[System Mode](#system-mode)**

**[Details of Files](#details-of-files)**

**[Conclusion](#conclusion)**

## Objective

The primary goal of this project is to design andimplement a real-time image processing application using Apache Storm. Theapplication aims to showcase distributed computing principles by processingvideo frames in parallel, applying various image filters, and aggregating theresults. The project emphasizes the utilization of Apache Storm, a powerful andscalable distributed stream processing framework, to achieve efficient andreal-time image processing.

**Key Features and Objectives:**

2.  **Real-time Stream Processing:** Implement a streaming data processing pipeline that operates in real-time, demonstrating the capabilities of Apache Storm for handling continuous streams of data.

3.  **Parallel Image Processing:** Utilize distributed computing principles to process video frames in parallel. This involves breaking down the image processing tasks into smaller, independent units that can be executed concurrently on multiple nodes.

4.  **Image Filtering:** Apply image filters to enhance or modify the visual characteristics of each frame. Image processing libraries, such as OpenCV for Java, will be employed to implement various filters, such as Gaussian blur, sharpening, and grayscale conversion.

5.  **Aggregation of Results:** Aggregate the processed frames to produce meaningful insights or visual effects. For example, combining frames processed with different filters to generate a composite image.

## Framework and Tools

- **Primary Framework:** Apache Storm will serve as the primary framework for building the distributed stream processing application. Apache Storm excels in handling real-time data streams and provides fault tolerance and scalability.

- **Programming Language:** The project will be implemented in Java, although other suitable languages may be considered. Java is chosen for its compatibility with Apache Storm and the availability of libraries like OpenCV.

- **Image Processing Libraries:** OpenCV for Java (OpenCV-Java) will be used as the primary image processing library. OpenCV provides a wide range of functionalities for image and video processing, making it a suitable choice for implementing diverse image filters.

## Expected Outcome

The successful completion of this project willresult in a robust, real-time image processing application built on ApacheStorm. The application will showcase the distributed computing capabilities ofApache Storm while demonstrating advanced image processing techniques. Theoutcomes may include visually enhanced video streams, performance metricsrelated to parallel processing, and insights into the efficiency of real-timestream processing with Apache Storm.

## Importance

This project is significant as it combines theprinciples of distributed computing and real-time stream processing to addresschallenges related to continuous data streams, such as those encountered in videoprocessing. The application serves as a practical example of leveraging ApacheStorm for distributed computing tasks, demonstrating its potential in handlinglarge-scale, real-time data processing applications.

## Main Concepts in Apache Storm

**1\. Topology:**

- A Storm topology is a directed acyclic graph (DAG) of spouts and bolts, defining the data flow in the system.

- Spouts are the sources of data, often responsible for reading from external streams or queues.

- Bolts are the processing units that receive and process the data emitted by spouts or other bolts.

**2\. Spouts:**

- Spouts are the entry points for data into a Storm topology.

- They are responsible for fetching and emitting streams of data for further processing.

- Spouts can read data from various sources, such as Kafka, Twitter, or any other streaming platform.

**3\. Bolts:**

- Bolts process and transform the data received from spouts or other bolts.

- They perform specific operations on the data, such as filtering, aggregation, or enrichment.

- Bolts emit new streams, allowing for the creation of complex processing pipelines.

**4\. Tuple:**

- A tuple is the fundamental unit of data in Storm.

- It is an ordered set of values, similar to a row in a database or a JSON object.

- Tuples are passed between spouts and bolts within the topology.

**5\. Stream:**

- A stream is an unbounded sequence of tuples in Storm.

- It represents a continuous flow of data that can be processed by the topology.

- Streams are the channels through which data is communicated between spouts and bolts.

**6\. Nimbus:**

- Nimbus is the master node in a Storm cluster responsible for distributing code and coordinating the execution of topologies.

- It accepts the topologies submitted by users and assigns tasks to Supervisor nodes.

**7\. Supervisor:**

- Supervisors are worker nodes responsible for executing tasks assigned by Nimbus.

- They run spouts and bolts as separate processes in a distributed environment.

**8\. Task:**

- A task is the smallest unit of parallelism in Storm.

- Each spout or bolt can have multiple tasks running in parallel across the cluster.

- Tasks process different subsets of the data, enabling parallel and scalable processing.

**9\. Storm UI:**

- The Storm UI provides a web-based interface to monitor and manage Storm clusters.

- It offers insights into the status and performance of running topologies, including metrics and logs.

**10\. Acknowledgment and Failures:**

- Storm ensures reliability through tuple acknowledgment and replay mechanisms.

- Bolts can acknowledge the successful processing of tuples, and failed tuples are replayed to maintain data integrity.

Apache Storm's main concepts enable the creation of fault-tolerant,scalable, and real-time data processing systems. By understanding thesecomponents, developers can design and implement distributed processingtopologies tailored to their specific use cases.

## System Mode

In the proposed system architecture, the main objective is to leveragethe distributed processing capabilities of Apache Storm for real-time imagefiltering. The system is designed to handle video streams and apply twodistinct types of image processing elements (PEs): PE1, responsible forapplying a Gaussian blur filter, and PE2, which applies a sharpening filter toeach frame of the video.

**1\. Distributed Processing:**

- Apache Storm is employed as the primary framework for distributed processing.

- The system is designed to operate in a distributed environment, utilizing multiple nodes to process video frames in parallel.

**2\. Processing Elements (PEs):**

- **PE1 (Gaussian Blur):**

- **PE2 (Sharpening):**

**3\. Frame Aggregation:**

- Develops a Processing Element (PE3) responsible for combining the output of PE1 and PE2.

- PE3 sums up the matrices resulting from the applied filters, creating a composite frame that incorporates both the Gaussian blur and sharpening effects.

**4\. Output Creation:**

- A Processing Element (PE4) is implemented to generate a new video file with the filtered frames.

- PE4 utilizes Apache Storm's capabilities to process the combined frames and produce an output video file with the desired visual effects.

**5\. Frame Analysis:**

- The system includes mechanisms to analyze each frame and collect relevant information.

- A text file is generated containing frame analysis data, including metrics such as average brightness and frame count.

- The frame analysis data provides insights into the visual characteristics of the processed video.

**6\. Real-Time Processing:**

- The entire system operates in real-time, enabling the application of image filters to video frames as they are received.

- Apache Storm's parallel processing model ensures efficient and scalable handling of video streams.

## Details of Files

The **FileHandler** class in the preprocessing package of the imageand video processing application fulfills several essential functions relatedto file management and analysis. One primary responsibility is handled by the **clearExistingOutputFolder()**method, which effectively removes the contents of the output folder, providinga clean slate for storing processed frames. The **createOutputFolder()**method ensures the existence of the output folder, creating it if it does notalready exist. This function is crucial for maintaining an organized structurefor the processed output.

For the analysis phase, the **createAnalysisWriter(String filePath)**method initializes a **PrintWriter** to facilitate writing analysis data toa file. This includes individual frame brightness data, achieved through the **writeBrightnessToAnalysisFile()**method. Additionally, the **writeOverallBrightnessToAnalysisFile()** methodappends overall brightness statistics, such as the total frame count andaverage brightness, providing a comprehensive summary in the analysis file.

The **saveProcessedFrame(Mat frame, int frameCount, StringoutputFolder)** method is responsible for storing processed frames in theoutput folder. It constructs the file path based on the frame count and writesthe frame to that location using the OpenCV library's **Imgcodecs.imwrite()**.

To ensure proper resource management, the **closeAnalysisWriter(PrintWriterwriter)** method is implemented to close the **PrintWriter** once allnecessary data has been written. Lastly, the **displayStatistics(intframeCount, double averageBrightness)** method prints overall processingstatistics, such as the total frame count and average brightness, to theconsole. The **FileHandler** class plays a vital role in maintaining theintegrity of the output and providing valuable insights into the preprocessingstage of the application.

The **FrameAnalyzer** class, a part of the preprocessing package,encompasses essential functionalities for frame analysis in the image and videoprocessing application. The class consists of methods designed to transform andanalyze frames during the preprocessing stage.

The **convertToGray(Mat frame)** method is responsible for convertinga given frame from its original color format to grayscale using the OpenCVlibrary's **Imgproc.cvtColor()** function. This step simplifies subsequentprocessing tasks and enhances efficiency by reducing the frame to a singlechannel.

The **resizeFrame(Mat frame, Size size)** method facilitates resizingof frames to a specified size. Leveraging the OpenCV library's **Imgproc.resize()**function, this method generates a new frame with dimensions determined by theprovided **Size** parameter. Resizing frames can be crucial forstandardizing input dimensions and optimizing processing performance.

For brightness analysis, the **calculateAverageBrightness(Mat frame)**method computes the average brightness of a frame. Utilizing OpenCV's **Core.mean()**function, this method returns a **Scalar** object containing the averagebrightness value. This metric serves as a fundamental parameter for assessingand comparing the luminance of frames.

In summary, the **FrameAnalyzer** class plays a pivotal role inpreparing frames for further processing by converting them to grayscale,resizing them as needed, and extracting valuable information about theirbrightness. These operations collectively contribute to the preprocessingpipeline, enhancing the overall efficiency and effectiveness of the image andvideo processing application.

The **VideoProcess** class serves as a core component in the imageand video processing application, managing the processing pipeline for videoframes. The class interacts with video files, conducts frame analysis, andorchestrates the overall preprocessing workflow.

The class is equipped with constants, including the input video path (**INPUT_VIDEO_PATH**),output frame folder (**OUTPUT_FRAME_FOLDER**), analysis file path (**ANALYSIS_FILE_PATH**),and the size to which frames are resized (**RESIZED_FRAME_SIZE**). Theseconstants establish the foundational configuration for video processing.

Internally, the class utilizes instances of the **FileHandler** and **FrameAnalyzer**classes to handle file operations and conduct frame analysis, respectively. The**processVideo()** method encapsulates the entire video processing workflow.

Upon initiating video processing, the method starts by clearing anyexisting output frames in the designated folder using the **clearExistingOutputFolder()**method of the **FileHandler**. Subsequently, a new output folder is createdto store processed frames through the **createOutputFolder()** method.

The method iterates through each frame of the input video using theOpenCV **VideoCapture** class. For each frame, the **convertToGray()** and**resizeFrame()** methods of the **FrameAnalyzer** are employed totransform the frame to grayscale and resize it to the predefined dimensions.The processed frame is then saved to the output folder using the **saveProcessedFrame()**method of the **FileHandler**.

Brightness analysis is performed on the resized frame by calculating itsaverage brightness using the **calculateAverageBrightness()** method. Theaverage brightness value is accumulated for subsequent statistical analysis.

Simultaneously, the method updates the frame count and writes brightnessinformation to an analysis file using the **writeBrightnessToAnalysisFile()**method of the **FileHandler**.

After processing all frames, the method calculates the overall averagebrightness and writes it, along with other statistical information, to theanalysis file using the **writeOverallBrightnessToAnalysisFile()** method.The analysis file is then closed with the **closeAnalysisWriter()** method.

Finally, statistical information, including the total frame count andaverage brightness, is displayed to the console using the **displayStatistics()**method of the **FileHandler**.

In essence, the **VideoProcess** class encapsulates the logic forprocessing video frames, coordinating the activities of the **FileHandler**and **FrameAnalyzer** to achieve efficient and comprehensive preprocessingof input video data.

---

The **Spout** package in the context of Apache Storm typically playsa crucial role in handling the data source or stream origin within a Stormtopology. Spouts are responsible for emitting streams of tuples that areprocessed by bolts in the topology. In a real-time data processing scenario,spouts often connect to external systems, such as message queues or data feeds,to ingest data into the Storm topology. They serve as the entry point for datainto the distributed processing system.

The provided code snippet appears to be part of the preprocessing stepsrather than directly related to the spout functionality. The **FileSorter**class within this context handles the sorting of an array of **File**objects based on a specific numeric pattern extracted from their names. While thecode itself is not directly tied to spout functionality, such preprocessingsteps, including sorting files, are crucial in scenarios where the order ofprocessing is significant. In the case of streaming data from filesrepresenting frames of a video, sorting based on numeric identifiers ensuresthat frames are processed in a chronological order, which is often essential invideo processing applications. The **FileSorter** class aligns with thepreparatory steps taken before the streaming data is further processed withinthe Apache Storm topology.

The **ImageFrameSpout** class in Apache Storm operates as a vitalcomponent responsible for emitting tuples within the Storm topology. Byextending the **BaseRichSpout** class, it seamlessly integrates into theStorm framework. During the spout's initialization in the **open** method,the necessary setup occurs. This includes assigning the **SpoutOutputCollector**for tuple emission, obtaining and sorting the list of image files from thedesignated directory ("Files/Gray_Resize_frames"), and initializingthe **currentImageIndex** to signify the start of the image list.

In the **nextTuple** method, the spout proceeds to emit tuples. Itfirst checks if there are more images to process by ensuring that **currentImageIndex**falls within the bounds of the array. If an image is present, it reads theimage using OpenCV's **Imgcodecs.imread**. Upon confirming that the image isnot empty, it emits a tuple containing the image and the total number of images(**imageFiles.length**). This additional information may be valuable fordownstream processing. Following the emission, the **currentImageIndex** isincremented to prepare for the next iteration.

The **declareOutputFields** method plays a crucial role in specifyingthe output fields for the emitted tuple. In this specific implementation, itdeclares two fields: "frame" and "frameL," suggesting thatthe emitted tuple will encompass an image frame and information regarding thetotal number of frames. In summary, the **ImageFrameSpout** serves as theprimary entry point for image frames into the Storm topology, reading andemitting images from a specified directory. The emitted tuples subsequentlyundergo processing by downstream components within the Storm topology.Moreover, the spout is equipped to handle potential errors during itsoperation, providing error messages and stack traces for debugging purposes.

---

The **FrameProcessor** class within the sharpening package of the ApacheStorm project serves as a central component responsible for processing imageframes. This class encapsulates the logic for applying a sharpening filter toeach incoming tuple, which represents an image frame within the Storm topology.The **processFrame** method, triggered by the Storm framework, takes atuple, an output collector, and the current frame index as parameters. Itextracts the image frame and associated metadata, such as the frame number (**frameIndex**)and the total number of frames (**frameL**), from the tuple.

Inside the **processFrame** method, the **applySharpening**private method is invoked to perform the actual image processing. This methodapplies a sharpening filter to the input frame using OpenCV's image processingfunctions. Specifically, it first applies a Gaussian blur to the frame using **Imgproc.GaussianBlur**with a kernel size of (0, 0) and a standard deviation of 3. Subsequently, itperforms a weighted addition of the original frame and the blurred frame using **Core.addWeighted**.The resulting sharpened frame is then emitted as part of a new tuple withadditional information, such as the processing tag ("Sharpening"),the frame index, the sharpened frame itself, and the total number of frames.

In the event of any exceptions during the processing, the class isdesigned to catch and handle errors gracefully. It prints an error message tothe standard error stream, providing information about the encounteredexception, and outputs the associated stack trace for further diagnosticpurposes. Overall, the **FrameProcessor** class contributes to thedistributed image processing workflow by incorporating the sharpening filterinto the Storm topology, enabling parallelized real-time processing of videoframes.

The **SharpeningBolt** class, part of the Apache Storm project'ssharpening package, is a crucial component responsible for implementing thesharpening filter logic within the Storm topology. As a subclass of **BaseRichBolt**,it integrates seamlessly into the Storm framework, taking advantage of itsparallel processing capabilities. The class contains methods to prepare thebolt for execution, execute the processing logic for each incoming tuple, anddeclare the output fields for emitted tuples.

In the **prepare** method, the class initializes the outputcollector, establishing a link to the downstream components in the Stormtopology. This method is executed once before the bolt starts processingtuples, allowing for setup operations.

The core of the sharpening logic resides in the **execute** method.Here, an instance of the **FrameProcessor** class is created, and the **processFrame**method is invoked. This method takes the incoming tuple, the output collector,and the current frame index as parameters, encapsulating the logic for applyingthe sharpening filter to the image frame. The frame index is incremented afterprocessing each tuple, ensuring that the frames are processed sequentially.

The **declareOutputFields** method specifies the output fields for emittedtuples. In this case, the declared fields include the processing tag("tag"), the frame index ("index"), the processed frame("frame"), and the total number of frames ("frameL"). Thisinformation is crucial for downstream components to interpret and furtherprocess the emitted tuples.

To handle potential exceptions during execution, the **SharpeningBolt**class employs error handling mechanisms. In case of an exception, an errormessage is printed to the standard error stream, providing information about theencountered exception, and the associated stack trace is output for debuggingpurposes.

Overall, the **SharpeningBolt** class plays a vital role in thedistributed image processing workflow, integrating the sharpening filter intothe Storm topology for parallelized real-time processing of video frames.

---

The **FrameProcessor** class in the GaussianBlur package of theApache Storm project is a key component responsible for implementing theGaussian blur filter logic within the Storm topology. This class, like thecorresponding class in the Sharpening package, encapsulates the processinglogic for applying the Gaussian blur filter to image frames and emits theprocessed frames with additional information.

In the **processFrame** method, the incoming tuple is accessed toretrieve the image frame, frame index, and the total number of frames. The **applyGaussianBlur**method is then invoked, which applies the Gaussian blur filter to the image frameusing the OpenCV library. The processed frame is then emitted using the outputcollector, along with the processing tag ("GaussianBlur"), frameindex, and the total number of frames. Similar to the Sharpening package, theframe index is incremented after processing each tuple, ensuring sequentialprocessing of frames.

The **applyGaussianBlur** method utilizes OpenCV's **Imgproc.GaussianBlur**function to perform the Gaussian blur operation on the input frame. Thefunction takes parameters specifying the source frame, destination frame,kernel size (here set to 45x45), and standard deviations in the X and Ydirections (both set to 0, implying automatic computation based on the kernelsize).

To handle exceptions during execution, the class employs error handlingmechanisms similar to the Sharpening package. In case of an exception, an errormessage is printed to the standard error stream, providing information aboutthe encountered exception, and the associated stack trace is output fordebugging purposes.

Overall, the **FrameProcessor** class within the GaussianBlur packageintegrates the Gaussian blur filter into the Apache Storm topology,contributing to the real-time distributed image processing workflow.

The **GaussianBlurBolt** class, residing in the GaussianBlur packageof the Apache Storm project, is a key component responsible for integrating theGaussian blur processing logic into the Storm topology. This class extends the **BaseRichBolt**class and contributes to the real-time image processing workflow by executingthe Gaussian blur operation on the incoming image frames within the Stormtopology.

In the **prepare** method, the class initializes its outputcollector, which is later used to emit the processed frames. The **execute**method is where the core logic resides. Upon receiving a tuple, the **FrameProcessor**class is instantiated, and the **processFrame** method is invoked to applythe Gaussian blur filter to the image frame. The processed frame, along withadditional information such as the processing tag ("GaussianBlur"),frame index, and the total number of frames, is emitted using the outputcollector. The frame index is then incremented to ensure sequential processingof frames.

The **declareOutputFields** method specifies the output fieldsemitted by this bolt. In this case, the processing tag ("tag"), frameindex ("index"), processed frame ("frame"), and the totalnumber of frames ("frameL") are declared.

To handle exceptions during execution, the class utilizes a try-catchblock. In the event of an exception, an error message is printed to thestandard error stream, providing information about the encountered exception,and the associated stack trace is output for debugging purposes.

In summary, the **GaussianBlurBolt** class integrates the Gaussianblur processing logic into the Apache Storm topology, ensuring that theGaussian blur filter is applied to each incoming image frame in a distributedand parallelized manner. This contributes to the overall goal of achievingreal-time distributed image processing within the Storm framework.

---

The **Combiner** package in the Apache Storm project encompasses thelogic for combining frames processed with different filters, specificallyGaussian blur and sharpening, within a distributed and parallelized processingenvironment. This package consists of two files: **FrameMap** and **FrameCombinerBolt**.

**FrameMap Class**

The **FrameMap** class is designed to manage a mapping between frameindices and corresponding image frames (**Mat** objects). It serves as astorage structure for frames, allowing easy addition, retrieval, and removal offrames based on their indices.

**FrameCombinerBolt Class**

The **FrameCombinerBolt** class, extending the **BaseRichBolt**class, plays a crucial role in the Storm topology. In its **prepare**method, the class initializes the output collector, **FrameMap** instancesfor blurred and sharpened frames, and the current frame index.

In the **execute** method, the class processes incoming tuplesrepresenting frames. It categorizes frames based on the processing tag("GaussianBlur" or "Sharpening") and adds them to therespective **FrameMap**. If frames for both blur and sharpening filters areavailable for the current index, it combines them using the OpenCV **Core.addWeighted**method to create a blended frame.

The combined frame is emitted with additional information, including theframe index and the total number of frames, using the output collector. The **currentIndex**is then incremented to ensure sequential processing. Exception handling isimplemented to capture and log any errors that may occur during execution.

The **declareOutputFields** method specifies the output fieldsemitted by the bolt, including the combined frame, frame index, and the totalnumber of frames. This allows downstream bolts to consume the processed frames.

In summary, the **FrameCombinerBolt** class within the **Combiner**package is integral to the real-time image processing workflow, combiningframes processed with different filters and contributing to the overall goal ofachieving distributed and parallelized image processing within the Apache Stormframework.

---

The **Output** package in the Apache Storm project consists of threefiles, and the first file is named **FrameProcessor**. This class isresponsible for processing frames and generating the final output, includingwriting frames to files and creating a video file.

**FrameProcessor Class - File 1**

The **FrameProcessor** class provides a static method, **processFrame**,that takes a frame (**Mat** object), output folder path, original file name,and frameL as parameters. The method first checks if the input frame is emptyand logs an error if it is. It then proceeds to write the frame to a file inthe specified output folder using the **Imgcodecs.imwrite** method.

The method also checks if the current frame's index matches thespecified frameL. If so, it prepares to create a video file by sorting theframes in the output folder based on their indices. The sorting is done using acustom comparator that extracts frame numbers from file names and converts themto integers for comparison. The sorted frames are then passed to a helperclass, **VideoWriterHelper**, to create a video file using OpenCV's **VideoWriter**functionality.

Error handling is implemented to catch and log any exceptions that mayoccur during frame processing. The class contributes to the overall goal of theproject by finalizing the output, including individual frame files and thegenerated video file.

In summary, the **FrameProcessor** class in the **Output** packageplays a vital role in the finalization of the output by handling the writing ofindividual frames to files and the creation of a video file based on specifiedconditions, thereby concluding the real-time image processing workflow.

The **Output** package in the Apache Storm project consists of threefiles, and the second file includes two classes: **VideoWriterHelper** and **OutputerBolt**.These classes contribute to the finalization of the output by handling thecreation of a video file and processing frames within the Storm topology.

**VideoWriterHelper Class - File 2**

The **VideoWriterHelper** class provides a static method, **writeVideo**,which takes parameters such as the output video path, frame rate, frame size,and an array of files representing the sorted frames. The method utilizesOpenCV's **VideoWriter** to create a video file by reading each frame fromthe specified directory and writing it to the video. The class includes errorhandling to catch and log exceptions during the video writing process.

**OutputerBolt Class - File 3**

The **OutputerBolt** class extends the **BaseRichBolt** class andis responsible for processing frames within the Apache Storm topology. Itinitializes an output collector and a queue for frames. The class also ensuresthe existence of the output folder and loads the necessary native library. The **execute**method processes each tuple received from the topology, extracting the frame,original file name, and frameL. It then calls the static **processFrame**method from the **FrameProcessor** class to handle frame processing,including writing frames to files and creating a video file.

The **cleanup** method provides a place for additional cleanup logicif needed in the future, and the **declareOutputFields** method declares theoutput fields if necessary. The class includes error handling to catch and logany exceptions that may occur during the frame processing within the Stormtopology.

In summary, the **VideoWriterHelper** and **OutputerBolt** classesin the **Output** package contribute significantly to the finalization ofthe output by handling video creation and frame processing within the Stormtopology, respectively. They play crucial roles in concluding the real-timeimage processing workflow initiated by the project's objectives.

---

The **Topology** package in the Apache Storm project contains a single filenamed **ImageTopology**, which serves as the entry point for defining andrunning the distributed image processing topology. The **ImageTopology**class features a method named **runStormTopology()**, responsible for configuringand executing the Storm topology. Within this method, a **TopologyBuilder**object is created to define the structure of the topology. It includes thespecification of a spout named "frame-spout" for reading frames fromthe video source and two bolts, "gaussian-blur-bolt" and"sharpening-bolt," responsible for Gaussian blur and sharpeningoperations, respectively. These bolts are connected to the"frame-spout" spout to receive input frames. Additionally, a"combine-bolt" is defined to aggregate the output of the Gaussianblur and sharpening bolts, and an "output-bolt" is designated tohandle the final processed output. The method incorporates configurationsettings using a **Config** object and executes the topology locally througha **LocalCluster**, providing a convenient environment for testing anddebugging. Exception handling is implemented to catch and print any errors thatmay occur during the topology execution, aiding in the debugging process.Overall, the **ImageTopology** class encapsulates the necessary logic fororchestrating the distributed image processing workflow using Apache Storm.

---

In conclusion, the developed image processing application using ApacheStorm successfully leverages distributed computing principles to process videoframes in real-time. The project's primary goal was to demonstrate thecapabilities of Apache Storm for distributed stream processing while applyingimage filters and aggregating results. The chosen framework, Apache Storm,provided a robust and scalable platform for building the distributed imageprocessing topology.

The project's architecture is organized into several packages, eachfocusing on specific aspects of the image processing workflow. The **FileHandler**class within the **Preprocessing** package handles file operations,including clearing existing output folders and creating new ones. The **FrameAnalyzer**class, also in the **Preprocessing** package, deals with frame manipulationtasks, such as converting frames to grayscale, resizing, and calculatingaverage brightness.

The **Spout** package features the **ImageFrameSpout** class,responsible for emitting frames into the topology. This is complemented by the **FileSorter**class, which efficiently sorts image files based on frame numbers.

Two distinct packages, **GaussianBlur** and **Sharpening**,implement the image processing logic for applying Gaussian blur and sharpeningfilters, respectively. Each package consists of a **FrameProcessor** classand a corresponding Storm bolt class (**GaussianBlurBolt** and **SharpeningBolt**).These components ensure the parallel and distributed execution of imageprocessing tasks.

The **Combiner** package introduces the concept of frame combination,where the **FrameCombinerBolt** class merges the outputs of the Gaussianblur and sharpening bolts. This process demonstrates the coordination andsynchronization of parallel processing streams within the Storm topology.

Finally, the **Output** package, comprising the **FrameProcessor**,**VideoWriterHelper**, and **OutputerBolt** classes, handles the finalstages of the image processing workflow. It writes processed frames to anoutput folder, generates a new video file, and produces a text file containingframe analysis data.

The **Topology** package encapsulates the entire image processingtopology in the **ImageTopology** class. This class defines and orchestratesthe spouts, bolts, and their connections, creating a cohesive and scalabledistributed image processing system.

## Conclusion

In summary, the project achieves its goal of demonstrating distributedimage processing using Apache Storm. The modular design and organization intodistinct packages enhance code maintainability and readability, making iteasier to extend or modify the application in the future. The Apache Stormframework, coupled with the Java programming language and additional librarieslike OpenCV, provides a powerful and flexible environment for real-time streamprocessing applications.
